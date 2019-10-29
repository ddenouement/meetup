import {Component, OnInit} from '@angular/core';
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

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe(res => {
        if (res['userDTO'].roles[0] === "SPEAKER") {
          this.speaker = true;
        } else if (res['userDTO'].roles[0] === "ADMIN") {
          this.admin = true;
        } else {
          this.listener = true;
        }
      },
      error => {
        console.warn('error in sidebar (get): '+error);
      });

    const hamburger = document.querySelector(".hamburger");
    const bar = document.querySelector(".sidebar");
// On click
    hamburger.addEventListener("click", function () {
      // Toggle class "is-active"
      bar.classList.toggle("active");
      hamburger.classList.toggle("is-active");
      // Do something else, like open/close menu
    });
  }

}
