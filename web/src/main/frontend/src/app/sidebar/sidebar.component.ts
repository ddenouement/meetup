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
  isLoading = false;
  constructor(private httpClient: HttpClient, private  router: Router, private notificationsService: NotificationsService) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.httpClient.get(this.userURL).subscribe(res => {
        this.isLoading = false;
        if (res['userDTO'].roles.includes("SPEAKER")) {
          this.speaker = true;
        } else if (res['userDTO'].roles.includes("ADMIN")) {
          this.admin = true;
        } else {
          this.listener = true;
        }
        this.SIDEBAR_DATA = [
          {
            activeSRC: '../../assets/images/admTableActive.png',
            noActiveSRC: '../../assets/images/admTableNoActive.png',
            routerLink: '/admin-table',
            alt: 'table-users',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/dictionariesActive.png',
            noActiveSRC: '../../assets/images/dictionariesNoActive.png',
            routerLink: '/dictionaries',
            alt: 'dictionaries',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/createArticleActive.png',
            noActiveSRC: '../../assets/images/createArticleNoActive.png',
            routerLink: '/admin-badges',
            alt: 'admin-badges',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/myProfileActive.png',
            noActiveSRC: '../../assets/images/myProfileNoActive.png',
            routerLink: '/speaker-profile',
            alt: 'speaker-profile',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/subscriptionsActive.png',
            noActiveSRC: '../../assets/images/subscriptionsNoActive.png',
            routerLink: '/subscriptions',
            alt: 'subscriptions',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/subscriptionsActive.png',
            noActiveSRC: '../../assets/images/subscriptionsNoActive.png',
            routerLink: '/subscriptions',
            alt: 'subscriptions',
            role: this.listener,
            active: false
          },
          {
            activeSRC: '../../assets/images/notificationActive.png',
            noActiveSRC: '../../assets/images/notificationNoActive.png',
            routerLink: '/notifications',
            alt: 'notifications',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/createArticleActive.png',
            noActiveSRC: '../../assets/images/createArticleNoActive.png',
            routerLink: '/create-article',
            alt: 'create-article',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/meetupCreateActive.png',
            noActiveSRC: '../../assets/images/meetupCreateNoActive.png',
            routerLink: '/meetup-create',
            alt: 'meetup-create',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/meetupListActive.png',
            noActiveSRC: '../../assets/images/meetupListNoActive.png',
            routerLink: '/meetup-list',
            alt: 'meetup-list',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/meetupListActive.png',
            noActiveSRC: '../../assets/images/meetupListNoActive.png',
            routerLink: '/meetup-list',
            alt: 'meetup-list',
            role: this.listener,
            active: false
          },
          {
            activeSRC: '../../assets/images/myProfileActive.png',
            noActiveSRC: '../../assets/images/myProfileNoActive.png',
            routerLink: '/listener-profile',
            alt: 'listener-profile',
            role: this.listener,
            active: false
          },
          {
            activeSRC: '../../assets/images/notificationActive.png',
            noActiveSRC: '../../assets/images/notificationNoActive.png',
            routerLink: '/notifications',
            alt: 'notifications',
            role: this.listener,
            active: false
          },
          {
            activeSRC: '../../assets/images/articleActive.png',
            noActiveSRC: '../../assets/images/articleNoActive.png',
            routerLink: '/article-list',
            alt: 'article-list',
            role: true,
            active: false
          },
          {
            activeSRC: '../../assets/images/feedbackActive.png',
            noActiveSRC: '../../assets/images/feedbackNoActive.png',
            routerLink: '/feedback',
            alt: 'feedback',
            role: this.speaker,
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
    this.isLoading = true;
    this.notificationsService.countAll().subscribe((res: number) => {
      this.isLoading = false;
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
