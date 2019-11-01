import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-listener-profile-to-users',
  templateUrl: './listener-profile-to-users.component.html',
  styleUrls: ['./listener-profile-to-users.component.scss']
})
export class ListenerProfileToUsersComponent implements OnInit {
  public badgeList: string[] = [];
  private userURL = '/api/v1/user/profile';
  private login;
  private email;

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe(res => {
      this.badgeList = res['badges'];
      this.login = res['userDTO'].login;
      this.email = res['userDTO'].email;
    });
  }

}
