import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginResponse} from "../models/loginResponse";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  public error = '';
  public loading = false;
  public login: string;
  public password: string;

  constructor(
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
    let logResponse = <LoginResponse>{
      login: this.loginForm.get('login').value,
      role: '',
      token: ''
    };

    this.loading = true;
    this.httpClient.post("/api/v1/user/login", user)
      //.pipe(map((res: Response) => res.json().then(function (data) {
      // logResponse = {
      //   login: data.login,
      //   role: data.role,
      //   token: data.token
      // };
      // if (data.role === "SPEAKER") {
      //   this.router.navigate(['/speaker-profile']);
      // } else {
      //   this.router.navigate(['/']);
      // }
      //})))
      .subscribe(data => {
          this.router.navigate(['/speaker-profile']);
        },
        error => {
          this.error = error.error;
          this.loading = false;
        });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}
