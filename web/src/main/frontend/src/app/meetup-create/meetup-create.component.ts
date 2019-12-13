import {Component, OnInit} from '@angular/core';

import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {MeetupsService} from "../services/meetups.service";
import {Duration} from "../models/duration.model";
import {User} from "../models/user";
import {LanguagesList} from "../models/languagesList";
import {Topic} from "../models/topic";
import {MeetupDto} from "../models/meetupDto.model";
import {ErrorStateMatcher} from "@angular/material/core";

class CrossFieldErrorMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return control.dirty && form.invalid;
  }
}
@Component({
  selector: 'app-meetup-create',
  templateUrl: './meetup-create.component.html',
  styleUrls: ['./meetup-create.component.scss']
})
export class MeetupCreateComponent implements OnInit {

  languagesList: LanguagesList[];
  topicsList: Topic[];


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
  errorMatcher = new CrossFieldErrorMatcher();
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
  minAtt : number;
  maxAtt : number;
  errorText: string;

  constructor(public meetupService: MeetupsService,
              private router: Router,
              private route: ActivatedRoute, private fb: FormBuilder) {
    this.initForm();
  }

  ngOnInit(): void {
    this.minDate.setMinutes(0);
    this.route.paramMap.subscribe((paramMap: ParamMap)=>{
      if(paramMap.has('meetupId')){
        this.mode = 'edit';
        this.meetupId = paramMap.get('meetupId');
        this.isLoading = true;
        this.meetupService.getMeetup(+this.meetupId).subscribe(meetupData =>{
          this.isLoading = false;
          this.meetup = meetupData.meetup;
          this.meetupTopic = meetupData.meetup.topic;
          this.meetupLang = meetupData.meetup.language;
          this.meetupDurationMinutes = meetupData.meetup.durationMinutes;
          const toSelectDuration = this.durations.find(d => d.minutes == this.meetupDurationMinutes);
          this.form.get('durationMinutes').setValue(toSelectDuration);
          this.meetupService.getSpeakerLanguages().subscribe(langData=>{
            this.languagesList = langData;
            const toSelectLanguage = this.languagesList.find(l => l.name == this.meetupLang.name);
            this.form.get('language').setValue(toSelectLanguage);
          });
          this.meetupService.getTopics().subscribe(topicsData=>{
            this.topicsList = topicsData;
            const toSelectTopic = this.topicsList.find(l => l.name == this.meetupTopic.name);
            this.form.get('topic').setValue(toSelectTopic);
          });
        });
      }else{
        this.mode ='create';
        this.meetupId = null;
        this.meetupService.getSpeakerLanguages().subscribe(langData=>{
          this.languagesList = langData;
        });
        this.meetupService.getTopics().subscribe(topicsData=>{
          this.topicsList = topicsData;
        });
      }
    });

  }

  initForm(){
    this.form = this.fb.group({
      title: new FormControl(null, {
        validators: [Validators.required, Validators.minLength(2)]
      }),
      durationMinutes: new FormControl('', [Validators.required]),
      language: new FormControl('',[Validators.required]),
      minAttendees: new FormControl(null, {validators: [Validators.required]}),
      maxAttendees: new FormControl(null, {validators: [Validators.required]}),
      description: new FormControl(null, {validators: [Validators.required, Validators.minLength(10)]}),
      topic: new FormControl('',[Validators.required]),
      time: new FormControl(this.minDate, [Validators.required]),
      date: new FormControl( null ),
    },{
      validators: [this.attendeesValidator],

    })
  };

  onSaveMeetup(){
    this.time = new Date (this.form.get('time').value);
    this.date = new Date (this.form.get('date').value);
    this.minAtt =this.form.value.minAttendees;
    this.maxAtt = this.form.value.maxAttendees;
    if (this.form.invalid){
      return;
    }

    this.isLoading = true;

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
  attendeesValidator(form: FormGroup) {
    const condition = form.get('maxAttendees').value < form.get('minAttendees').value;

    return condition ? { amountAttendeesInvalid: true} : null;
  }
  timeValidator(form: FormGroup) {
    const minutes = form.get('time').value;
    const condition = minutes.getMinutes() != 0 || minutes.getMinutes() != 10 || minutes.getMinutes() != 30 || minutes.getMinutes() != 45;
    return condition ? { minutesValidator: true} : null;
  }
}

