import {Component, OnDestroy, OnInit} from '@angular/core';
import { Subscription} from "rxjs";

import {Meetup} from "../models/meetup.model";
import {MeetupsService} from "../services/meetups.service";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-meetup-list',
  templateUrl: './meetup-list.component.html',
  styleUrls: ['./meetup-list.component.scss']
})
export class MeetupListComponent implements OnInit, OnDestroy{

  meetups : Meetup[] = [];
  totalMeetups = 30;
  meetupsPerPage = 10;
  currentPage = 1;
  pageSizeOptions = [5,10,20,30];
  private meetingsSub: Subscription;

  constructor(public meetupsService: MeetupsService){}

  ngOnInit(): void {
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage);

    //set up listener to subject
    this.meetingsSub = this.meetupsService.getMeetupUpdateListener()
      .subscribe((meetupData: { meetups: Meetup[] })=>{
        this.meetups = meetupData.meetups;
      });
  }

  onChangePage(pageData: PageEvent){
    this.currentPage = pageData.pageIndex + 1;
    this.meetupsPerPage = pageData.pageSize;
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage)
  }

  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
    this.meetingsSub.unsubscribe();
  }


}
