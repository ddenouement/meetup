import { Component, OnInit } from '@angular/core';
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  private admin = false;
  private speaker = false;
  private listener = false;
  private userURL = '/api/v1/user/profile';

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe(res => {
        if (res['UserDTO'].roles[0] === "SPEAKER") {
          this.speaker = true;
        } else if(res['UserDTO'].roles[0] === "ADMIN") {
          this.admin = true;
        }else {
          this.listener = true;
        }
      },
      error => {
        console.warn(error);
      });
  }

}
