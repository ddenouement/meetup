

import { Component, OnInit } from '@angular/core';
import {Meetup} from "../models/meetup.model";
import {MeetupsService} from "../services/meetups.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {MeetupDto} from "../models/meetupDto.model";
import {Subscription} from "rxjs";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-meetup-profile',
  templateUrl: './meetup-profile.component.html',
  styleUrls: ['./meetup-profile.component.scss']
})
export class MeetupProfileComponent implements OnInit {

  meetup: MeetupDto;
  private meetupId: string;
  private userId: number;
  meetupDate: Date;
  joinedCount : number;
  isLoading = false;
  isAutor = false;
  joinText = "JOIN";
  joined = false;
  private meetupSub: Subscription;

  constructor(public meetupService: MeetupsService,public userService: UserService,  public route: ActivatedRoute) {
  }
  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('meetupId')) {
        this.meetupId = paramMap.get('meetupId');
        this.isLoading = true;
        this.userService.getUserId().subscribe(res=>{
          this.userId = res;
        });
        this.meetupService.getMeetup(+this.meetupId)
          .subscribe(meetupData =>{
          this.isLoading = false;
          this.meetup = meetupData.meetup;
          this.meetupDate = meetupData.meetup.startDate;
          // this.meetupDate = this.convertUTCDateToLocalDate(meetupData.meetup.startDate);
          // this.meetup.startDate.setDate(this.meetup.)
          this.joined = meetupData.ifJoinedMeetup;
          if(this.joined){
            this.joinText = "LEAVE";
          }else{
            this.joinText = "JOIN";
          }
          if(this.meetup.speaker.id == this.userId){
            this.isAutor = true;
          }
        });
        this.isLoading = true;
        this.meetupService.getJoinedCount(+this.meetupId);
        this.meetupSub = this.meetupService.getMeetupJoinedUpdateListener()
        .subscribe(count =>{
          this.isLoading = false;
          this.joinedCount = count;
        });
      }
    });
  }
  onJoin(){
    this.isLoading = true;
    if (!this.joined) {
      this.isLoading = true;
      this.meetupService.joinMeetup(+this.meetupId).subscribe( res=>{
        this.isLoading = false;
        this.joined = true;
        this.joinText = 'LEAVE';
        this.meetupService.getJoinedCount(+this.meetupId);

      });
    } else {
      this.isLoading = true;
      this.meetupService.leaveMeetup(+this.meetupId).subscribe(res => {
        this.isLoading = false;
        this.joined = false;
        this.joinText = 'JOIN';
          this.meetupService.getJoinedCount(+this.meetupId);

        });
    }
  }
  onStart(){
    this.isLoading = true;

  }

  public convertUTCDateToLocalDate(date) {
    var newDate = new Date(date.getTime()+date.getTimezoneOffset()*60*1000);

    var offset = date.getTimezoneOffset() / 60;
    var hours = date.getHours();

    newDate.setHours(hours - offset);

    return newDate;
  }

}
