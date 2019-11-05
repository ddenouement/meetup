import { Component, OnInit } from '@angular/core';
import {Notification} from "../models/notification";
import {NotificationsService} from "./notifications.service";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {

  notifications: Notification[] = [];
  message: string = "Your meetup <a class=\'link\' [routerLink]=\"[\'/meetup-profile\',17]\">Meetup2</a> starts in 15 minutes.";

  constructor(public notificationsService: NotificationsService) { }

  ngOnInit() {
    this.notificationsService.getAll().subscribe((res: Notification[]) => {
      this.notifications = res;
    }, error => {
      console.log(error.error.message);
    });
  }

}
