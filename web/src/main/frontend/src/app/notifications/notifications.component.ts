import { Component, OnInit } from '@angular/core';
import {Notification} from "../models/notification";
import {NotificationsService} from "./notifications.service";
import {CommentDto} from "../models/commentDto";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {

  notifications: Notification[] = [];

  constructor(public notificationsService: NotificationsService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.notificationsService.getAll().subscribe((res: Notification[]) => {
      this.notifications = res;
    }, error => {
      this.snackBar.open('Error loading notifications');
    });
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
