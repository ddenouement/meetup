import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserToSubscribe} from "../models/userToSubscribe";

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.scss']
})
export class SubscribeComponent implements OnInit {
  users: UserToSubscribe [] = [];
  private userURL = '/api/v1/user/profile';
  public id: number;
  public firstName: string;
  public lastName: string;
  public login: string;

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe((res: { users: UserToSubscribe[] })=>{
      this.users = res['subscribedTo'];
    });
  }

}
