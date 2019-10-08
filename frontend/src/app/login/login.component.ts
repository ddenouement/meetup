import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    login: new FormControl(''),
    password: new FormControl(''),
  });

  public login: string;
  public password: string;

  constructor(
    private httpClient: HttpClient
  ) { }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    //console.warn(this.registrForm.value);
    this.do_login();
  }

  public do_login(): void {
    const user = <Authentificationrequest>{login: this.loginForm.get('login').value, password: this.loginForm.get('password').value};
    this.httpClient.post("/api/v1/user/login", user).subscribe();
  }

  ngOnInit() {
  }


}
