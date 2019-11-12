

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
  private meetingsSub: Subscription;
  isLoading = false;
  isAutor = false;
  joinText = "JOIN";
  joined = false;

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
        // this.meetingsSub = this.meetupService.getMeetupJoinedUpdateListener()
          .subscribe(meetupData =>{
          this.isLoading = false;
          this.meetup = meetupData.meetup;
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
      }
    });
  }
  onJoin(){
    this.isLoading = true;
    if (!this.joined) {
      this.meetupService.joinMeetup(+this.meetupId).subscribe(res => {
        this.isLoading = false;
        this.joined = true;
        this.joinText = 'LEAVE';
      });
    } else {
        this.meetupService.leaveMeetup(+this.meetupId).subscribe(res => {
        this.isLoading = false;
        this.joined = false;
        this.joinText = 'JOIN';
      });
    }
  }

}
