import {Component, OnDestroy, OnInit} from '@angular/core';

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
  totalMeetups: number;
  meetupsPerPage = 9;
  currentPage = 1;
  pageSizeOptions = [9,12,18,24,36,42];
  isLoading = false;

  constructor(public meetupsService: MeetupsService){}

  ngOnInit(): void {
    this.isLoading = true;
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage)
      .subscribe(meetupsData =>{
        this.isLoading=false;
        this.meetups = meetupsData.meetups;
        this.totalMeetups = meetupsData.meetupCount;
      });

  }

  onChangePage(pageData: PageEvent){
    this.currentPage = pageData.pageIndex + 1;
    this.meetupsPerPage = pageData.pageSize;
    this.isLoading = true;
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage)
      .subscribe(meetupsData =>{
        this.isLoading=false;
        this.meetups = meetupsData.meetups;
        this.totalMeetups = meetupsData.meetupCount;
      });
  }

  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
  }


}
