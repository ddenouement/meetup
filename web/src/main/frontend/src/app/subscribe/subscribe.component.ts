import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserToSubscribe} from "../models/userToSubscribe";
import {SpeakerProfileToUsersService} from "../speaker-profile-to-users/speaker-profile-to-users.service";
import {SubscribeService} from "./subscribe.service";

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.scss']
})
export class SubscribeComponent implements OnInit {
  users: UserToSubscribe [] = [];

  public id: number;
  public firstName: string;
  public lastName: string;
  public login: string;
  public star: number;

  constructor(private httpClient: HttpClient,public subscribeService: SubscribeService,) { }

  ngOnInit() {
    this.subscribeService.getUsers().subscribe((res: { users: UserToSubscribe[] })=>{
      this.users = res['subscribedTo'];
    });
  }

}
