import {Component, OnInit} from '@angular/core';

import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {MeetupsService} from "../services/meetups.service";
import {Meetup} from "../models/meetup.model";
import {Duration} from "../models/duration.model";
// import {Language} from "../models/language";
// import {Languagesservice} from "../services/languagessservice";
import {Observable} from "rxjs";
import {User} from "../models/user";

@Component({
  selector: 'app-meetup-create',
  templateUrl: './meetup-create.component.html',
  styleUrls: ['./meetup-create.component.scss']
})
export class MeetupCreateComponent implements OnInit {

  languagesList: string[] = ['Ukrainian', 'English', 'Polish', 'German', 'Spanish', 'Turkish'];
  durations: Duration[] = [
    {title: '30 min', minutes: 30},
    {title: '1 h', minutes: 60},
    {title: '1h 30min', minutes: 90},
    {title: '2 h', minutes: 120},
  ];
  meetup: Meetup;
  form: FormGroup;
  private mode = 'create';
  private meetupId: string;
  speaker : User;

  constructor(public meetupService: MeetupsService,  private router: Router, private route: ActivatedRoute) {
  }


  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap: ParamMap)=>{
      if(paramMap.has('meetupId')){
        this.mode = 'edit';
        this.meetupId = paramMap.get('meetupId');
        this.meetupService.getMeetup(+this.meetupId).subscribe(meetupData =>{
          this.meetup = meetupData;
        });
        // this.languages = this.langService.getLanguages();

      }else{
        this.mode ='create';
        this.meetupId = null;
      }
    });


    this.form = new FormGroup({
      title: new FormControl(null, {
        validators: [Validators.required, Validators.minLength(3)]
      }),
      startDate: new FormControl(null, {validators: [Validators.required]}),
      durationMinutes: new FormControl('', [Validators.required]),
      // language: new FormControl('',[Validators.required]),
      minAttendees: new FormControl(null, {validators: [Validators.required]}),
      maxAttendees: new FormControl(null, {validators: [Validators.required]}),
      description: new FormControl(null, {validators: [Validators.required]})
    });
  }

  onSaveMeetup(){
    if (this.form.invalid){
      return;
    }
    if(this.mode === "create"){
      this.meetupService.addMeetup(
        this.form.value.title,
        this.form.value.startDate,
        this.form.value.durationMinutes.minutes,
        this.form.value.minAttendees,
        this.form.value.maxAttendees,
        this.form.value.description
      );
    }else{
      this.meetupService.updateMeetup(
        +this.meetupId,
        this.form.value.title,
        this.meetup.speaker,
        this.meetup.state,
        this.form.value.startDate,
        this.form.value.durationMinutes.minutes,
        this.form.value.minAttendees,
        this.form.value.maxAttendees,
        this.form.value.description
      );
    }
    this.router.navigate([`/meetup-list`], { relativeTo: this.route });
    // this.form.reset();
  }
}

