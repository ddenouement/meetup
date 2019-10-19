import {Component, OnInit} from '@angular/core';
import {User} from "../models/user";
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ErrorStateMatcher} from "@angular/material/core";
import {StarRatingComponent} from "ng-starrating";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-speaker-profile',
  templateUrl: './speaker-profile.component.html',
  styleUrls: ['./speaker-profile.component.scss']
})
export class SpeakerProfileComponent implements OnInit {

  changeForm: FormGroup;
  public loading = false;
  matcher = new MyErrorStateMatcher();
  languagesList: string[] = ['Ukrainian', 'English', 'Polish', 'German', 'Spanish', 'Turkish'];
  public firstName: string;
  public lastName: string;
  public login: string;
  public email: string;
  public about: string;

  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
  ) {
  }

  onSubmit() {
    this.changeProfile();
  }

  private changeProfile() {
    const user = <User>{
      firstName: this.changeForm.get('firstName').value,
      lastName: this.changeForm.get('lastName').value,
      login: this.changeForm.get('login').value,
      email: this.changeForm.get('email').value,
      about: this.changeForm.get('about').value
    };
    this.loading = true;
  }

  ngOnInit() {
    this.changeForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      about: [''],
      languages: ['', Validators.required],
    });
    const hamburger = document.querySelector(".hamburger");
    const bar = document.querySelector(".sidebar");
// On click
    hamburger.addEventListener("click", function () {
      // Toggle class "is-active"
      bar.classList.toggle("active");
      hamburger.classList.toggle("is-active");
      // Do something else, like open/close menu
    });
  }

  onRate($event:{oldValue:number, newValue:number, starRating:StarRatingComponent}) {
    alert(`Old Value:${$event.oldValue}, 
      New Value: ${$event.newValue}, 
      Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }


}
