import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Sidebar} from "../models/sidebar";
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
            activeSRC: '../../assets/images/admTableActive.svg',
            noActiveSRC: '../../assets/images/admTableNoActive.svg',
            routerLink: '/admin-table',
            alt: 'table-users',
            role: this.admin,
            active: false
          },
          {
            //nema
            activeSRC: '../../assets/images/teamActive.svg',
            noActiveSRC: '../../assets/images/teamNoActive.svg',
            routerLink: '/admin-badges',
            alt: 'edit-badges',
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
            activeSRC: '../../assets/images/createArticleActive.svg',
            noActiveSRC: '../../assets/images/createArticleNoActive.svg',
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
            activeSRC: '../../assets/images/feedbackActive.svg',
            noActiveSRC: '../../assets/images/feedbackNoActive.svg',
            routerLink: '/feedback',
            alt: 'feedback',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/notificationActive.svg',
            noActiveSRC: '../../assets/images/notificationNoActive.svg',
            routerLink: '/notifications',
            alt: 'notifications',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/meetupCreateActive.svg',
            noActiveSRC: '../../assets/images/myProfileNoActive.svg',
            routerLink: '/meetup-create',
            alt: 'meetup-create',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/meetupListActive.svg',
            noActiveSRC: '../../assets/images/meetupListNoActive.svg',
            routerLink: '/meetup-list',
            alt: 'meetup-list',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/notificationsActive.svg',
            noActiveSRC: '../../assets/images/notificationsNoActive.svg',
            routerLink: '/notifications',
            alt: 'notifications',
            role: this.listener,
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
          {
            activeSRC: '../../assets/images/articleActive.svg',
            noActiveSRC: '../../assets/images/articleNoActive.svg',
            routerLink: '/article-list',
            alt: 'article-list',
            role: true,
            active: false
          },
          {
            activeSRC: '../../assets/images/teamActive.svg',
            noActiveSRC: '../../assets/images/teamNoActive.svg',
            routerLink: '/dictionaries',
            alt: 'dictionaries',
            role: this.admin,
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
