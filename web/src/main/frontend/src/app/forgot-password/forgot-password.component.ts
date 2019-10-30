import {Component, OnInit} from '@angular/core';
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
import {Router} from "@angular/router";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})

export class ForgotPasswordComponent implements OnInit {

  forgotForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  public loading = false;
  public error: '';
  public login: string;
  public email: string;


  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  get form() {
    return this.forgotForm.controls;
  }

  onSubmit() {
    this.approve();
  }

  public approve(): void {
    const user = <User>{
      login: this.forgotForm.get('login').value,
      email: this.forgotForm.get('email').value
    };
    this.loading = true;
    this.forgotForm.controls['login'].disable();
    this.forgotForm.controls['email'].disable();
    this.httpClient.post("/api/v1/user/register/speaker", user).subscribe(data => {
        this.router.navigate(['/verify']);
      },
      error => {
        this.loading = false;
        this.error = error.error;
        console.log(error);
        this.forgotForm.controls['login'].enable();
        this.forgotForm.controls['email'].enable();
      });
  }

  ngOnInit() {
    this.forgotForm = this.formBuilder.group({
      login: ['', Validators.required],
      email: ['', [Validators.email, Validators.required]],
    });
  }
}
