import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register-speaker',
  templateUrl: './register-speaker.component.html',
  styleUrls: ['./register-speaker.component.css']
})

export class RegisterSpeakerComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;

  public login: string;
  public email: string;
  public password: string;


  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
  ) {
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.register();
  }

  public register(): void {
    const user = <User>{
      login: this.registerForm.get('login').value,
      email: this.registerForm.get('email').value,
      password: this.registerForm.get('password').value
    };
    this.httpClient.post("/api/v1/user/register/speaker", user).subscribe();
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });

    // TODO: change to password-strength
    const pass = document.querySelector('.password');
    const complexityItem = document.getElementsByClassName('complexity__item');
    const complexity = document.querySelector('.complexity');
    const content = document.querySelector('.content');

    content.addEventListener('click', (event) => {
      // Toggle class "is-active"
      if (event.target === pass) {
        complexity.classList.add('active');
      } else {
        complexity.classList.remove('active');
      }

      pass.addEventListener('input', (eve) => {
        const lengthOfPass = (<HTMLInputElement>eve.target).value.split('').length;
        if (lengthOfPass == 0) {
          complexity.classList.remove('active');
        } else if (lengthOfPass <= 7) {
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.remove('complexity__item--good');
            complexityItem[i].classList.remove('complexity__item--perfect');
          }
          complexityItem[0].classList.add('complexity__item--bad');
        } else if (lengthOfPass <= 8 && (<HTMLInputElement>eve.target).value.match(/[A-Z]/i)) {
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.remove('complexity__item--bad');
            complexityItem[i].classList.remove('complexity__item--perfect');
          }
          for (let i = 0; i < complexityItem.length - 1; i++) {
            complexityItem[i].classList.add('complexity__item--good');
          }
        } else if (lengthOfPass > 8 && (<HTMLInputElement>eve.target).value.match(/[A-Z]/i) && (<HTMLInputElement>eve.target).value.match(/\d/i) && (<HTMLInputElement>eve.target).value.match(/[a-z]/i)) {
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.remove('complexity__item--bad');
            complexityItem[i].classList.remove('complexity__item--good');
          }
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.add('complexity__item--perfect');
          }
        }
      });
    });
  }
}

export function MustMatch(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup) => {
    const control = formGroup.controls[controlName];
    const matchingControl = formGroup.controls[matchingControlName];

    if (matchingControl.errors && !matchingControl.errors.mustMatch) {
      // return if another validator has already found an error on the matchingControl
      return;
    }

    // set error on matchingControl if validation fails
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({mustMatch: true});
    } else {
      matchingControl.setErrors(null);
    }
  }
}
