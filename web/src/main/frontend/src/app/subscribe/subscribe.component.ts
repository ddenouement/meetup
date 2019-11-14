import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserToSubscribe} from "../models/userToSubscribe";
import {SubscribeService} from "./subscribe.service";

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.scss']
})
export class SubscribeComponent implements OnInit {
  private users: UserToSubscribe [] = [];

  constructor(public subscribeService: SubscribeService,) { }

  ngOnInit() {
    this.subscribeService.getUsers().subscribe((res: { users: UserToSubscribe[] })=>{
      this.users = res['subscribedTo'];
    });
  }

}
