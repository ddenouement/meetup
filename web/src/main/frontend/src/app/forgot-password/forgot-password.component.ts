import {Component, OnDestroy, OnInit} from '@angular/core';
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
import {RegisterService} from "../services/register.service";
import {Subscription} from "rxjs";

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

export class ForgotPasswordComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();
  private forgotForm: FormGroup;
  private matcher = new MyErrorStateMatcher();
  private loading = false;
  private error: '';


  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
    private regService: RegisterService
  ) {
  }

  get form() {
    return this.forgotForm.controls;
  }

  onSubmit() {
    this.approve();
  }

  public approve(): void {
    this.loading = true;
    this.forgotForm.controls['email'].disable();
    let mail = this.forgotForm.get('email').value;
    this.subscriptions.add(this.regService.forgotPassword(mail).subscribe(data => {
        this.router.navigate(['/verify']);
      },
      error => {
        this.loading = false;
        this.error = error.error;
        this.forgotForm.controls['email'].enable();
      }));
  }

  ngOnInit() {
    this.forgotForm = this.formBuilder.group({
      email: ['', [Validators.email, Validators.required]],
    });
  }
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
