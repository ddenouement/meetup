import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Meetup} from "../models/meetup.model";
import {Subscription} from "rxjs";
import {MeetupsService} from "../services/meetups.service";
import {ListenerProfileService} from "./listener-profile.service";

@Component({
  selector: 'app-listener-profile',
  templateUrl: './listener-profile.component.html',
  styleUrls: ['./listener-profile.component.scss']
})
export class ListenerProfileComponent implements OnInit {
  private badgeList: string[] = [];
  private login;
  private email;
  private star: number;
  private userFutureMeetups: Meetup[] = [];
  private userMeetupFutureSub: Subscription;
  private userPastMeetups: Meetup[] = [];

  constructor(private httpClient: HttpClient, private meetupsService: MeetupsService, private listenerService: ListenerProfileService
  ) {
  }

  ngOnInit() {
    this.listenerService.getListener().subscribe(res => {
      this.badgeList = res['badges'];
      this.login = res['userDTO'].login;
      this.email = res['userDTO'].email;
      this.star = res['userDTO'].rate;
    });
    this.meetupsService.getUserFutureMeetups();
    this.userMeetupFutureSub = this.meetupsService.getUserFutureMeetupUpdateListener()
      .subscribe(meetupsData => {
        this.userFutureMeetups = meetupsData.meetups;
      });
    this.meetupsService.getUserPastMeetups().subscribe(meetupsData => {
      this.userPastMeetups = meetupsData;
    })
  }

  onLeave(id: number) {
    this.meetupsService.leaveMeetup(id).subscribe(res => {
      this.meetupsService.getUserFutureMeetups();

    });
  }
}
