// @ts-ignore
import {Component, OnInit, ViewChild} from '@angular/core';
// @ts-ignore
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
// @ts-ignore
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ErrorStateMatcher} from '@angular/material/core';
import {MatPasswordStrengthComponent} from '@angular-material-extensions/password-strength';
import {Router} from "@angular/router";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

// @ts-ignore
@Component({
  selector: 'app-register-speaker',
  templateUrl: './register-speaker.component.html',
  styleUrls: ['./register-speaker.component.css']
})

export class RegisterSpeakerComponent implements OnInit {

  @ViewChild('passwordComponentWithConfirmation', {static: true})
  passwordComponentWithConfirmation: MatPasswordStrengthComponent;

  registerForm: FormGroup;
  submitted = false;
  matcher = new MyErrorStateMatcher();
  //TODO create database of languages
  languagesList: string[] = ['Ukrainian', 'English', 'Polish', 'German', 'Spanish', 'Turkish'];

  showDetails3: boolean;
  public loading = false;
  public error: '';
  public firstName: string;
  public lastName: string;
  public login: string;
  public email: string;
  public about: string;
  public password: string;

  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    //console.log(this.registerForm);
    this.register();
  }

  onStrengthChanged(strength: number) {
  }

  public register(): void {
    const user = <User>{
      firstName: this.registerForm.get('firstName').value,
      lastName: this.registerForm.get('lastName').value,
      login: this.registerForm.get('login').value,
      email: this.registerForm.get('email').value,
      about: this.registerForm.get('about').value,
      password: this.passwordComponentWithConfirmation.password
    };
    this.loading = true;
    this.httpClient.post("/api/v1/user/register/speaker", user).subscribe(data => {
        this.router.navigate(['/login']);
      },
      error => {
        this.error = error.error;
        this.loading = false;
      });
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      about: [''],
      languages: ['', Validators.required],
      password: ['', Validators.required],
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
