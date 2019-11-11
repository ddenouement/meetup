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
import {MeetupDto} from "../models/meetupDto.model";
import DateTimeFormat = Intl.DateTimeFormat;
// TODO: validation of date, time;
@Component({
  selector: 'app-meetup-create',
  templateUrl: './meetup-create.component.html',
  styleUrls: ['./meetup-create.component.scss']
})
export class MeetupCreateComponent implements OnInit {

  languagesList: LanguagesList[];
  topicsList: Topic[];
  private langsSub: Subscription;
  private topicsSub: Subscription;
  private meetingsSub: Subscription;


  durations: Duration[] = [
    {title: '30 min', minutes: 30},
    {title: '1 h', minutes: 60},
    {title: '1h 30min', minutes: 90},
    {title: '2 h', minutes: 120},
    {title: '2h 30min', minutes: 150},
    {title: '3 h', minutes: 180},
    {title: '3h 30min', minutes: 210},
    {title: '4 h', minutes: 240},
    {title: '4h 30min', minutes: 270},
    {title: '5 h', minutes: 300},
    {title: '5h 30min', minutes: 330},
    {title: '6 h', minutes: 360},
  ];
  meetup: MeetupDto;
  form: FormGroup;
  private mode = 'create';
  isLoading = false;
  private meetupId: string;
  private meetupLang : LanguagesList;
  private meetupTopic : Topic;
  private meetupDurationMinutes: number;
  speaker : User;
  minDate = new Date(Date.now());
  time : Date;
  date : Date;
  startDate : Date;


  constructor(public meetupService: MeetupsService,
              private router: Router,
              private route: ActivatedRoute) {  }


  ngOnInit(): void {
    this.minDate.setMinutes(0);
    this.form = new FormGroup({
      title: new FormControl(null, {
        validators: [Validators.required, Validators.minLength(3)]
      }),
      durationMinutes: new FormControl('', [Validators.required]),
      language: new FormControl('',[Validators.required]),
      minAttendees: new FormControl(null, {validators: [Validators.required]}),
      maxAttendees: new FormControl(null, {validators: [Validators.required]}),
      description: new FormControl(null, {validators: [Validators.required]}),
      topic: new FormControl('',[Validators.required]),
      time: new FormControl(this.minDate, [Validators.required]),
      date: new FormControl( null ),
    });

    this.route.paramMap.subscribe((paramMap: ParamMap)=>{
      if(paramMap.has('meetupId')){
        this.mode = 'edit';
        this.meetupId = paramMap.get('meetupId');
        this.isLoading = true;
        this.meetupService.getMeetup(+this.meetupId);
        this.meetingsSub = this.meetupService.getMeetupJoinedUpdateListener().subscribe(meetupData =>{
          this.isLoading = false;
          this.meetup = meetupData.meetup;
          this.meetupLang = meetupData.meetup.language;
          this.meetupDurationMinutes = meetupData.meetup.durationMinutes;
          const toSelectDuration = this.durations.find(d => d.minutes == this.meetupDurationMinutes);
          this.form.get('durationMinutes').setValue(toSelectDuration);
          this.meetupService.getLanguages().subscribe(langData=>{
            this.languagesList = langData;
            const toSelectLanguage = this.languagesList.find(l => l.name == this.meetupLang.name);
            this.form.get('language').setValue(toSelectLanguage);
          });
          this.meetupService.getTopics().subscribe(topicsData=>{
            this.topicsList = topicsData;
          });
        });
      }else{
        this.mode ='create';
        this.meetupId = null;
        this.meetupService.getLanguages().subscribe(langData=>{
          this.languagesList = langData;
        });
        this.meetupService.getTopics().subscribe(topicsData=>{
          this.topicsList = topicsData;
        });
      }
    });

  }

  onSaveMeetup(){
    if (this.form.invalid){
      return;
    }
    this.isLoading = true;
    this.time = this.form.get('time').value;
    this.date = this.form.get('date').value;

    this.startDate = new Date(this.date.getFullYear(), this.date.getMonth(), this.date.getDate(), this.time.getHours(), this.time.getMinutes(), 0)
    if(this.mode === "create"){
      this.meetupService.addMeetup(
        this.form.value.language.id,
        this.form.value.topic.id,
        this.form.value.title,
        this.startDate,
        this.form.value.durationMinutes.minutes,
        this.form.value.minAttendees,
        this.form.value.maxAttendees,
        this.form.value.description,
      );
    }else{
      this.meetupService.updateMeetup(
        +this.meetupId,
        this.form.value.title,
        this.form.value.language.id,
        this.meetup.speaker.id,
        this.form.value.topic.id,
        this.meetup.state,
        this.startDate,
        this.form.value.durationMinutes.minutes,
        this.form.value.minAttendees,
        this.form.value.maxAttendees,
        this.form.value.description,
      );
      this.isLoading = false;
    }
    this.router.navigate([`/meetup-list`], { relativeTo: this.route });
    // this.form.reset();
  }
}

