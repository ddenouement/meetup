import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Sidebar} from "../models/sidebar";
import {Notification} from "../models/notification";
import {NotificationsService} from "../notifications/notifications.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  public admin = false;
  public speaker = false;
  public listener = false;
  private userURL = '/api/v1/user/profile';
  public href: string = "";
  public SIDEBAR_DATA: Sidebar[] = [];
  public notificationCount = 0;

  constructor(private httpClient: HttpClient, private  router: Router, private notificationsService: NotificationsService) {
  }

  ngOnInit() {

    this.httpClient.get(this.userURL).subscribe(res => {
        if (res['userDTO'].roles.includes("SPEAKER")) {
          this.speaker = true;
        } else if (res['userDTO'].roles.includes("ADMIN")) {
          this.admin = true;
        } else {
          this.listener = true;
        }
        this.SIDEBAR_DATA = [
          {
            activeSRC: '../../assets/images/teamActive.svg',
            noActiveSRC: '../../assets/images/teamNoActive.svg',
            routerLink: '/admin-table',
            alt: 'admin-table',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/notification.svg',
            noActiveSRC: '../../assets/images/notification.svg',
            routerLink: '/create-article',
            alt: 'create-article',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/teamActive.svg',
            noActiveSRC: '../../assets/images/teamNoActive.svg',
            routerLink: '/subscriptions',
            alt: 'subscriptions',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/searchActive.svg',
            noActiveSRC: '../../assets/images/searchNoActive.svg',
            routerLink: '/create-article',
            alt: 'create-article',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/myProfileActive.svg',
            noActiveSRC: '../../assets/images/myProfileNoActive.svg',
            routerLink: '/speaker-profile',
            alt: 'speaker-profile',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/messagesNoActive.svg',
            noActiveSRC: '../../assets/images/messagesNoActive.svg',
            routerLink: '/feedback',
            alt: 'feedback',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/myProfileActive.svg',
            noActiveSRC: '../../assets/images/myProfileNoActive.svg',
            routerLink: '/listener-profile',
            alt: 'listener-profile',
            role: this.listener,
            active: false
          },
        ];
        this.href = this.router.url;
        for (let bar in this.SIDEBAR_DATA) {
          if (this.href.includes(this.SIDEBAR_DATA[bar].routerLink)) {
            this.SIDEBAR_DATA[bar].active = true;
          }
        }
      },
      error => {
        console.warn('error in sidebar (get): ' + error);
      });

    this.notificationsService.countAll().subscribe((res: number) => {
      this.notificationCount = res;
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

  hamburger() {

  }
}
