import {Component, OnDestroy, OnInit} from '@angular/core';
import { Subscription} from "rxjs";

import {Meetup} from "../models/meetup.model";
import {MeetupsService} from "../services/meetups.service";
import {PageEvent} from "@angular/material/paginator";
import {MeetupDto} from "../models/meetupDto.model";

@Component({
  selector: 'app-meetup-list',
  templateUrl: './meetup-list.component.html',
  styleUrls: ['./meetup-list.component.scss']
})
export class MeetupListComponent implements OnInit, OnDestroy{

  meetups : MeetupDto[] = [];
  totalMeetups = 30;
  meetupsPerPage = 5;
  currentPage = 1;
  pageSizeOptions = [5,10,20,30];
  isLoading = false;
  private meetingsSub: Subscription;

  constructor(public meetupsService: MeetupsService){}

  ngOnInit(): void {
    this.isLoading = true;
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage);
    //set up listener to subject
    this.meetingsSub = this.meetupsService.getMeetupDtoUpdateListener()
      .subscribe((meetupData: { meetups: MeetupDto[], meetupsCount: number })=>{
        this.isLoading=false;
        this.meetups = meetupData.meetups;
        this.totalMeetups = meetupData.meetupsCount;
      });
  }

  onChangePage(pageData: PageEvent){
    this.currentPage = pageData.pageIndex + 1;
    this.meetupsPerPage = pageData.pageSize;
    this.isLoading = true;
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage);
    this.meetingsSub = this.meetupsService.getMeetupDtoUpdateListener()
      .subscribe((meetupData: { meetups: MeetupDto[] })=>{
        this.isLoading=false;
        this.meetups = meetupData.meetups;
      });
  }

  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
    this.meetingsSub.unsubscribe();
  }


}
