import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from "@angular/material/core";
import {MatPasswordStrengthComponent} from '@angular-material-extensions/password-strength';
import {Router} from "@angular/router";
import {RegisterService} from "../register-speaker/register.service";
import {Registration} from "../models/registration";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {

  @ViewChild('passwordComponentWithConfirmation', {static: true})
  passwordComponentWithConfirmation: MatPasswordStrengthComponent;

  private registerForm: FormGroup;
  private loading = false;
  private error: '';
  private login: string;
  private email: string;
  private password: string;
  private matcher = new MyErrorStateMatcher();
  private showDetails3: boolean;


  constructor(
    private registerService: RegisterService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.sendMail();
    this.register();
  }

  onStrengthChanged(strength: number) {
  }

  public sendMail(): void {
    const user = <Registration>{
      login: this.registerForm.get('login').value,
      email: this.registerForm.get('email').value
    };
    this.registerService.sendUser(user).subscribe(res => {
      console.log('EMAIL SEND');
    },error1 => {
      console.error('Email ! send');
    });

  }

  public register(): void {
    const user = <User>{
      login: this.registerForm.get('login').value,
      email: this.registerForm.get('email').value,
      password: this.passwordComponentWithConfirmation.password
    };
    this.loading = true;
    this.registerForm.controls['login'].disable();
    this.registerForm.controls['email'].disable();
    this.registerForm.controls['password'].disable();
    this.registerForm.controls['confirmPassword'].disable();
    this.registerService.registerListener(user).subscribe(data => {
        this.router.navigate(['/verify']);
      },
      error => {
        this.error = error.error;
        console.log(error);
        this.loading = false;
        this.registerForm.controls['login'].enable();
        this.registerForm.controls['email'].enable();
        this.registerForm.controls['password'].enable();
        this.registerForm.controls['confirmPassword'].enable();
      });
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: MustMatch('password', 'confirmPassword')
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


