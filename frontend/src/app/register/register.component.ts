import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Listener} from "../models/listener";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public login: string;
  public email: string;
  public password: string;

  constructor(
    private httpClient: HttpClient
  ) { }

  public register(): void {
    const listener = <Listener>{login: this.login, email: this.email, password: this.password};
    this.httpClient.post("/api/register", listener).subscribe();
  }

  ngOnInit() {
  }

}
