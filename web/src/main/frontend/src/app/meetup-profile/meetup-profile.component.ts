

import { Component, OnInit } from '@angular/core';
import {Meetup} from "../models/meetup.model";
import {MeetupsService} from "../services/meetups.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {MeetupDto} from "../models/meetupDto.model";

@Component({
  selector: 'app-meetup-profile',
  templateUrl: './meetup-profile.component.html',
  styleUrls: ['./meetup-profile.component.scss']
})
export class MeetupProfileComponent implements OnInit {

  meetup: MeetupDto;
  private meetupId: string;
  private userId: number;
  isLoading = false;
  isAutor = false;
  joinText = "JOIN";

  constructor(public meetupService: MeetupsService,  public route: ActivatedRoute) {
  }
  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('meetupId')) {
        this.meetupId = paramMap.get('meetupId');
        this.isLoading = true;
        this.meetupService.getUserId().subscribe(res=>{
          this.userId = res;
        });
        this.meetupService.getMeetup(+this.meetupId).subscribe(meetupData =>{
          this.isLoading = false;
          this.meetup = meetupData;
          if(this.meetup.speaker.id == this.userId){
            this.isAutor = true;
          }
        });

      }
    });
  }
  onJoin(){
    this.isLoading = true;
    if (this.joinText === "JOIN") {
      this.meetupService.joinMeetup(+this.meetupId).subscribe(res => {
        this.isLoading = false;
        this.joinText = 'LEAVE';
      });
    } else {
      this.meetupService.leaveMeetup(+this.meetupId).subscribe(res => {
        this.isLoading = false;
        this.joinText = 'JOIN';
      });
    }
  }

}
