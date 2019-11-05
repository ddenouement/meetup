import {Component, OnInit} from '@angular/core';

import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {MeetupsService} from "../services/meetups.service";
import {Meetup} from "../models/meetup.model";
import {Duration} from "../models/duration.model";
import {Languagesservice} from "../services/languagessservice";
import {Observable, Subscription} from "rxjs";
import {User} from "../models/user";
import {Topicsservice} from "../services/Topicsservice";
import {TopicClass} from "../models/topic_class";
import {LanguagesList} from "../models/languagesList";
import {Topic} from "../models/topic";

@Component({
  selector: 'app-meetup-create',
  templateUrl: './meetup-create.component.html',
  styleUrls: ['./meetup-create.component.scss']
})
export class MeetupCreateComponent implements OnInit {

  languagesList: LanguagesList[];
  language: LanguagesList;
  private langsSub: Subscription;

  topicList : Topic [];
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

  constructor(public meetupService: MeetupsService,
              public langService: Languagesservice,
              public topicService : Topicsservice,
              private router: Router,
              private route: ActivatedRoute) {  }


  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap: ParamMap)=>{
      if(paramMap.has('meetupId')){
        this.mode = 'edit';
        this.meetupId = paramMap.get('meetupId');
        this.meetupService.getMeetup(+this.meetupId).subscribe(meetupData =>{
          this.meetup = meetupData;
          this.language = meetupData.language;
          // this.topicList = meetupData.topics;
        });
      }else{
        this.mode ='create';
        this.meetupId = null;
        this.meetupService.getLanguages();
        this.langsSub = this.meetupService.getLanguageUpdateListener()
          .subscribe((langsData: { languages: LanguagesList[] })=>{
            this.languagesList = langsData.languages;
          });
        // this.topicList = this.topicService.getTopics();
      }
    });


    this.form = new FormGroup({
      title: new FormControl(null, {
        validators: [Validators.required, Validators.minLength(3)]
      }),
      startDate: new FormControl(null, {validators: [Validators.required]}),
      durationMinutes: new FormControl('', [Validators.required]),
      language: new FormControl('',[Validators.required]),
      minAttendees: new FormControl(null, {validators: [Validators.required]}),
      maxAttendees: new FormControl(null, {validators: [Validators.required]}),
      description: new FormControl(null, {validators: [Validators.required]}),
      topics: new FormControl('',[Validators.required])
    });
  }

  onSaveMeetup(){
    if (this.form.invalid){
      return;
    }
    if(this.mode === "create"){
      this.meetupService.addMeetup(
        this.form.value.language,
        this.form.value.title,
        this.form.value.startDate,
        this.form.value.durationMinutes.minutes,
        this.form.value.minAttendees,
        this.form.value.maxAttendees,
        this.form.value.description,
        this.form.value.topics
      );
    }else{
      this.meetupService.updateMeetup(
        +this.meetupId,
        this.form.value.title,
        this.meetup.speaker,
        this.form.value.language,
        this.meetup.state,
        this.form.value.startDate,
        this.form.value.durationMinutes.minutes,
        this.form.value.minAttendees,
        this.form.value.maxAttendees,
        this.form.value.description,
        this.form.value.topics
      );
    }
    this.router.navigate([`/meetup-list`], { relativeTo: this.route });
    // this.form.reset();
  }
}

