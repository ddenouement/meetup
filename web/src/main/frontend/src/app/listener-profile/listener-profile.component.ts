import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {RegisterService} from "../register-speaker/register.service";

@Component({
  selector: 'app-listener-profile',
  templateUrl: './listener-profile.component.html',
  styleUrls: ['./listener-profile.component.scss']
})
export class ListenerProfileComponent implements OnInit {
  public badgeList: string[] = [];
  private userURL = '/api/v1/user/profile';
  private login;
  private email;
  public star: number;

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe(res => {
      this.badgeList = res['badges'];
      this.login = res['userDTO'].login;
      this.email = res['userDTO'].email;
      this.star =res['userDTO'].rate;
    });
  }

}
