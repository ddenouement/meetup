import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted = false;

  public login: string;
  public password: string;

  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
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
    this.httpClient.post("/api/v1/user/login", user).subscribe();
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}
