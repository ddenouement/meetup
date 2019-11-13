import {Component, OnDestroy, OnInit} from '@angular/core';
import {Notification} from "../models/notification";
import {NotificationsService} from "./notifications.service";
import {MatSnackBar} from "@angular/material";
import {UserService} from "../services/user.service";
import { Message } from "@stomp/stompjs";
import {MessagingService} from "../services/messaging.service";

const NOTIFICATIONS_URL = "/topic/notifications";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit, OnDestroy {

  notifications: Notification[] = [];
  isLoading = false;
  private userLogin;
  private messagingService: MessagingService;

  constructor(private notificationsService: NotificationsService,
              private userService: UserService,
              private snackBar: MatSnackBar) {
    // this.initializeWebSocketConnection();

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
          '/user/' + that.userLogin + NOTIFICATIONS_URL);

        // Subscribe to its stream (to listen on messages)
        this.messagingService.stream().subscribe((message: Message) => {
          let n : Notification = JSON.parse(message.body);
          this.notifications.unshift(n);
        });
      }
    )
  }

  ngOnInit() {

    this.isLoading = true;
    this.notificationsService.getAll().subscribe((res: Notification[]) => {
      this.notifications = res;
      this.isLoading = false;
    }, error => {
      this.snackBar.open('Error loading notifications');
    });
  }

  ngOnDestroy(): void {
    this.messagingService.disconnect();
  }

  readNotification(notification: Notification) {
    this.notificationsService.read(notification.id).subscribe(data => {
        this.notifications.splice(this.notifications.indexOf(notification),1);
      },
      error => {
        this.snackBar.open('Error deleting notification');
      }
    )
  }

}
