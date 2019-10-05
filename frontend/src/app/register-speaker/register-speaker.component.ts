import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Speaker} from "../models/speaker";

@Component({
  selector: 'app-register-speaker',
  templateUrl: './register-speaker.component.html',
  styleUrls: ['./register-speaker.component.css']
})
export class RegisterSpeakerComponent implements OnInit {

  public login: string;
  public email: string;
  public password: string;
  public name: string;
  public surname: string;
  public nativeLanguage: string;


  constructor(
    private httpClient: HttpClient
  ) { }

  public register(): void {
    const speaker = <Speaker>{login: this.login, email: this.email, password: this.password, name: this.name, surname: this.surname, nativeLanguage: this.nativeLanguage};
    this.httpClient.post("/api/v1/user/register/speaker", speaker).subscribe();
  }

  ngOnInit() {
  }

}
