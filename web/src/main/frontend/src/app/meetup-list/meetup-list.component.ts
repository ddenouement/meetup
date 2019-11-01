import {Component, OnDestroy, OnInit} from '@angular/core';
import { Subscription} from "rxjs";

import {Meetup} from "../models/meetup.model";
import {MeetupsService} from "../services/meetups.service";

@Component({
  selector: 'app-meetup-list',
  templateUrl: './meetup-list.component.html',
  styleUrls: ['./meetup-list.component.scss']
})
export class MeetupListComponent implements OnInit, OnDestroy{

  meetups : Meetup[] = [];
  private meetingsSub: Subscription;

  constructor(public meetupsService: MeetupsService){}

  ngOnInit(): void {
    this.meetupsService.getMeetups();

    //set up listener to subject
    this.meetingsSub = this.meetupsService.getMeetupUpdateListener()
      .subscribe((meetupData: { meetups: Meetup[] })=>{
        this.meetups = meetupData.meetups;
      });
  }

  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
    this.meetingsSub.unsubscribe();
  }


}
