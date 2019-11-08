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

  constructor(public meetupService: MeetupsService,  public route: ActivatedRoute) {
  }
  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('meetupId')) {
        this.meetupId = paramMap.get('meetupId');
        this.meetupService.getMeetup(+this.meetupId).subscribe(meetupData =>{
          this.meetup = meetupData;
        });
      }
    });
  }

}
