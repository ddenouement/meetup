import {Component, OnInit} from '@angular/core';
import {User} from "../models/user";
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ErrorStateMatcher} from "@angular/material/core";
import {StarRatingComponent} from "ng-starrating";
import {RegisterService} from "../register-speaker/register.service";
import {LanguagesList} from "../models/languagesList";
import {MustMatch} from "../register-speaker/register-speaker.component";
import {Meetup} from "../models/meetup.model";
import {Subscription} from "rxjs";
import {MeetupsService} from "../services/meetups.service";

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
  languages: LanguagesList [];
  public speakerId : number;
  public badgeList: string[] = [];
  public firstName: string;
  public lastName: string;
  public login: string;
  public email: string;
  public about: string;
  private userURL = 'http://localhost:9990/api/v1/user/profile';
  speakerMeetups : Meetup[] = [];
  private meetingsSub: Subscription;

  constructor(
    private httpClient: HttpClient,
    public meetupsService: MeetupsService,
    private formBuilder: FormBuilder,
    private registerService: RegisterService,
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
    let langList: string[] = [];
    this.httpClient.get(this.userURL).subscribe(res => {
      for (let i in res['userDTO'].languages) {
        langList[i] = res['userDTO'].languages[i].name;
      }
      this.speakerId = res['userDTO'].id;
      // console.log("SPEAKER ID" + this.speakerId);
      this.badgeList = res['badges'];
      this.changeForm = this.formBuilder.group({
        firstName: [res['userDTO'].firstName, Validators.required],
        lastName: [res['userDTO'].lastName, Validators.required],
        login: [res['userDTO'].login, Validators.required],
        email: [res['userDTO'].email, [Validators.required, Validators.email]],
        about: [res['userDTO'].about],
        languages: ['', Validators.required],
      });
    });

    this.registerService.getLanguages().subscribe(
      res => {
          this.languages = res;
        },
        err => {
          console.log(err);
        });

    // this.meetupsService.getSpeakerMeetups(this.speakerId);
    // //set up listener to subject
    // this.meetingsSub = this.meetupsService.getSpeakerMeetupUpdateListener()
    //   .subscribe((meetupData: { meetups: Meetup[] })=>{
    //     this.speakerMeetups = meetupData.meetups;
    //   });

  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    alert(`Old Value:${$event.oldValue}, 
      New Value: ${$event.newValue}, 
      Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

}
