import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Sidebar} from "../models/sidebar";
import {NotificationsService} from "../notifications/notifications.service";
import {UserService} from "../services/user.service";
import {MessagingService} from "../services/messaging.service";
import {Message} from "@stomp/stompjs";
import {MatSnackBar} from "@angular/material";

const NOTIFICATION_COUNT_URL = "/topic/notification-count";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit, OnDestroy {
  private admin = false;
  private speaker = false;
  private listener = false;
  private href: string = "";
  private SIDEBAR_DATA: Sidebar[] = [];
  private notificationCount = 0;
  private isLoading = false;
  private userLogin;
  private messagingService: MessagingService;

  constructor(private httpClient: HttpClient, private  router: Router,
              private route: ActivatedRoute,
              private notificationsService: NotificationsService,
              private userService: UserService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.userService.getUserProfile().subscribe(res => {
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
            activeSRC: '../../assets/images/admTableActive.svg',
            noActiveSRC: '../../assets/images/admTableNoActive.svg',
            routerLink: '/admin-table',
            alt: 'table-users',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/dictionariesActive.svg',
            noActiveSRC: '../../assets/images/dictionariesNoActive.svg',
            routerLink: '/dictionaries',
            alt: 'dictionaries',
            role: this.admin,
            active: false
          },
          {
            activeSRC: '../../assets/images/createArticleActive.svg',
            noActiveSRC: '../../assets/images/createArticleNoActive.svg',
            routerLink: '/admin-badges',
            alt: 'admin-badges',
            role: this.admin,
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
            activeSRC: '../../assets/images/subscriptionsActive.svg',
            noActiveSRC: '../../assets/images/subscriptionsNoActive.svg',
            routerLink: '/subscriptions',
            alt: 'subscriptions',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/subscriptionsActive.svg',
            noActiveSRC: '../../assets/images/subscriptionsNoActive.svg',
            routerLink: '/subscriptions',
            alt: 'subscriptions',
            role: this.listener,
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
            activeSRC: '../../assets/images/createArticleActive.svg',
            noActiveSRC: '../../assets/images/createArticleNoActive.svg',
            routerLink: '/create-article',
            alt: 'create-article',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/meetupCreateActive.svg',
            noActiveSRC: '../../assets/images/meetupCreateNoActive.svg',
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
            activeSRC: '../../assets/images/meetupListActive.svg',
            noActiveSRC: '../../assets/images/meetupListNoActive.svg',
            routerLink: '/meetup-list',
            alt: 'meetup-list',
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
            activeSRC: '../../assets/images/notificationActive.svg',
            noActiveSRC: '../../assets/images/notificationNoActive.svg',
            routerLink: '/notifications',
            alt: 'notifications',
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
            activeSRC: '../../assets/images/settingsActive.svg',
            noActiveSRC: '../../assets/images/settingsNoActive.svg',
            routerLink: '/change-password',
            alt: 'change-password',
            role: this.speaker,
            active: false
          },
          {
            activeSRC: '../../assets/images/settingsActive.svg',
            noActiveSRC: '../../assets/images/settingsNoActive.svg',
            routerLink: '/change-password',
            alt: 'change-password',
            role: this.listener,
            active: false
          }
        ];
        this.route.paramMap.subscribe((paramMap: ParamMap) => {
          if (!(paramMap.has('speakerId') || paramMap.has('listenerId'))) {
            this.href = this.router.url;
            for (let bar in this.SIDEBAR_DATA) {
              if (this.href.includes(this.SIDEBAR_DATA[bar].routerLink)) {
                this.SIDEBAR_DATA[bar].active = true;
              }
            }
          }
        });
      });
    this.isLoading = true;
    this.notificationsService.countAll().subscribe((res: number) => {
      this.isLoading = false;
      this.notificationCount = res;
    });
    this.userService.getUserLogin().subscribe(data => {
        this.userLogin = data;
        let that = this;

        // Instantiate a messagingService
        let prefix = "wss://";
        if (window.location.hostname === "localhost") {
          prefix = "ws://";
        }
        this.messagingService = new MessagingService(
          prefix + window.location.host + "/socket",
          '/user/' + that.userLogin + NOTIFICATION_COUNT_URL);

        // Subscribe to its stream (to listen on messages)
        this.messagingService.stream().subscribe((message: Message) => {
          this.notificationCount = +message.body;
          this.snackBar.open("New notification!");
        });
      }
    );
  }

  ngOnDestroy(): void {
    this.messagingService.disconnect();
  }

  hamburger() {
    const hamburger = document.querySelector(".hamburger");
    const bar = document.querySelector(".sidebar");
    bar.classList.toggle("active");
    hamburger.classList.toggle("is-active");
  }
}
