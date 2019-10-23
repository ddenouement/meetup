import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterService} from "../register-speaker/register.service";
import {LoginService} from "./login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  public error = '';
  public loading = false;
  public login: string;
  public password: string;

  private logInURL = '/api/v1/user/login';

  constructor(
    public loginService: LoginService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  onSubmit() {
    this.do_login();
  }

  public do_login(): void {
    const user = <Authentificationrequest>{
      login: this.loginForm.get('login').value,
      password: this.loginForm.get('password').value
    };
    this.loading = true;
    this.loginForm.controls['login'].disable();
    this.loginForm.controls['password'].disable();
    this.httpClient.post(this.logInURL, user)
      .subscribe(data => {
          if (data['role'] === "SPEAKER") {
            this.router.navigate(['/speaker-profile']);
          } else {
            this.router.navigate(['/']);
          }
          this.loginService.logInUserBool = true;
        },
        error => {
          this.error = error.error;
          console.log(error);
          //this.loading = false;
          this.loginForm.controls['login'].enable();
          this.loginForm.controls['password'].enable();
        });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}
