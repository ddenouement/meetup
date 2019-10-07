import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public login: string;
  public password: string;

  constructor(
    private httpClient: HttpClient
  ) { }

  public do_login(): void {
    const user = <Authentificationrequest>{login: this.login,   password: this.password};
    this.httpClient.post("/api/v1/user/login", user).subscribe();
  }

  ngOnInit() {
  }


}
