import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  Validators
} from "@angular/forms";
import {ErrorStateMatcher} from '@angular/material/core';
import {MatPasswordStrengthComponent} from '@angular-material-extensions/password-strength';
import {Router} from "@angular/router";
import {RegisterService} from "./register.service";
import {LanguagesList} from "../models/languagesList";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register-speaker',
  templateUrl: './register-speaker.component.html',
  styleUrls: ['./register-speaker.component.scss']
})

export class RegisterSpeakerComponent implements OnInit {

  @ViewChild('passwordComponentWithConfirmation', {static: true})
  passwordComponentWithConfirmation: MatPasswordStrengthComponent;

  registerForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  languages: LanguagesList [];
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
    public registerService: RegisterService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.register();
  }

  onStrengthChanged(strength: number) {
  }

  public register(): void {
    let langList: number[] = [];
    for (let i in this.registerForm.get('languages').value) {
      langList[i] = this.registerForm.get('languages').value[i].id;
    }
    const user = <User>{
      firstName: this.registerForm.get('firstName').value,
      lastName: this.registerForm.get('lastName').value,
      login: this.registerForm.get('login').value,
      email: this.registerForm.get('email').value,
      about: this.registerForm.get('about').value,
      languageIds: langList,
      password: this.passwordComponentWithConfirmation.password
    };
    this.loading = true;
    this.registerForm.controls['firstName'].disable();
    this.registerForm.controls['lastName'].disable();
    this.registerForm.controls['login'].disable();
    this.registerForm.controls['about'].disable();
    this.registerForm.controls['languages'].disable();
    this.registerForm.controls['email'].disable();
    this.registerForm.controls['password'].disable();
    this.registerForm.controls['confirmPassword'].disable();
    this.httpClient.post("/api/v1/user/register/speaker", user).subscribe(data => {
        this.router.navigate(['/verify']);
      },
      error => {
        this.loading = false;
        this.error = error.error;
        console.log(error);
        this.registerForm.controls['firstName'].enable();
        this.registerForm.controls['lastName'].enable();
        this.registerForm.controls['login'].enable();
        this.registerForm.controls['about'].enable();
        this.registerForm.controls['languages'].enable();
        this.registerForm.controls['email'].enable();
        this.registerForm.controls['password'].enable();
        this.registerForm.controls['confirmPassword'].enable();
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

    this.registerService.getLanguages()
      .subscribe(
        languages => {
          this.languages = languages;
        },
        err => {
          console.log(err);
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
