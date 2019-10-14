import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  public error = '';
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
    this.httpClient.post("/api/v1/user/login", user)
    .subscribe(data => {
        this.router.navigate(['/']);
    },
    error => {
      this.error = error.error.error;
    });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}
