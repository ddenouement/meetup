import {Component, OnDestroy, OnInit} from '@angular/core';

import {MeetupsService} from "../services/meetups.service";
import {PageEvent} from "@angular/material/paginator";
import {MeetupDto} from "../models/meetupDto.model";
import {FilterService} from "../services/filter.service";
import {Filter} from "../models/filter.model";

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
  filter:Filter  = {
    id: null,
    id_user: null,
    name : null,
    id_language : null,
    rate_from : 0,
    rate_to: 5,
    time_from : null,
    time_to : null,
    topic_id : null,
    title_substring: null,
  };
  filteredMeetups: MeetupDto[] = [];
  constructor(public meetupsService: MeetupsService,public filterService: FilterService){}

  ngOnInit(): void {
    this.isLoading = true;
    this.meetupsService.getMeetups(this.meetupsPerPage, this.currentPage)
      .subscribe(meetupsData =>{
        this.isLoading=false;
        this.meetups = meetupsData.meetups;
        this.totalMeetups = meetupsData.meetupCount;
      });
    // this.meetupsService.getMeetupsFiltered(this.filter,this.meetupsPerPage, this.currentPage).subscribe(meetupsData =>{
    //   this.isLoading=false;
    //   this.meetups = meetupsData.meetups;
    //   this.totalMeetups = meetupsData.meetupCount;
    // });
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
    // this.meetupsService.getMeetupsFiltered(this.filter,this.meetupsPerPage, this.currentPage).subscribe(meetupsData =>{
    //   this.isLoading=false;
    //   this.meetups = meetupsData.meetups;
    //   this.totalMeetups = meetupsData.meetupCount;
    // });

  }

  receiveFilter($event){
    this.filter = $event;
    this.meetupsService.getMeetupsFiltered(this.filter,this.meetupsPerPage, this.currentPage).subscribe(meetupsData =>{
      this.isLoading=false;
      this.meetups = meetupsData.meetups;
      this.totalMeetups = meetupsData.meetupCount;
    });

  }
  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
  }


}
