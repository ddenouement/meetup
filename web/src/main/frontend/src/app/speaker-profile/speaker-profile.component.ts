import {Component, OnInit} from '@angular/core';
import {User} from "../models/user";
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ErrorStateMatcher} from "@angular/material/core";
import {StarRatingComponent} from "ng-starrating";
import {RegisterService} from "../services/register.service";
import {LanguagesList} from "../models/languagesList";
import {MustMatch} from "../register-speaker/register-speaker.component";
import {Meetup} from "../models/meetup.model";
import {Subscription} from "rxjs";
import {MeetupsService} from "../services/meetups.service";
import {SpeakerProfileService} from "../services/speaker-profile.service";
import {Router} from "@angular/router";
import {MeetupDto} from "../models/meetupDto.model";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-speaker-profile',
  templateUrl: './speaker-profile.component.html',
  styleUrls: ['./speaker-profile.component.scss']
})
export class SpeakerProfileComponent implements OnInit {

  private changeForm: FormGroup;
  private loading = false;
  private matcher = new MyErrorStateMatcher();
  private languages: LanguagesList [];
  private selectedLanguages: LanguagesList [];
  private speakerId: number;
  private badgeList: string[] = [];
  private langListNames: string[] = [];
  private firstName: string;
  private lastName: string;
  private login: string;
  private email: string;
  private about: string;
  private star: number;
  private edited = false;
  private selected: any;
  private load= false;

  speakerFutureMeetups:Meetup[] = [];
  private meetupFutureSub: Subscription;
  speakerPastMeetups:Meetup[] = [];
  private meetupPastSub: Subscription;
  userFutureMeetups:Meetup[] = [];
  private userMeetupFutureSub: Subscription;
  userPastMeetups:Meetup[] = [];

  terminated = {
    id: 6,
    true: "TERMINATED"
  };

  constructor(
    private httpClient: HttpClient,
    private meetupsService: MeetupsService,
    private formBuilder: FormBuilder,
    private registerService: RegisterService,
    private speakerService: SpeakerProfileService,
  ) {
  }

  onSubmit() {
    this.changeProfile();
  }

  private changeProfile() {
    let langList: number[] = [];
    for (let i in this.changeForm.get('languages').value) {
      langList[i] = this.changeForm.get('languages').value[i].id;
    }
    const user = <User>{
      firstName: this.changeForm.get('firstName').value,
      lastName: this.changeForm.get('lastName').value,
      login: this.changeForm.get('login').value,
      email: this.changeForm.get('email').value,
      about: this.changeForm.get('about').value,
      languageIds: langList,
    };
    this.loading = true;
    this.speakerService.updateUser(user).subscribe(res => {
      this.ngOnInit();
      this.loading = false;
      this.edited = false;
    }, error => {
      this.loading = false;
    });
  }

  ngOnInit() {
    this.changeForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      about: [''],
      languages: ['', Validators.required]
    });
    this.loading = true;
    this.speakerService.getSpeaker().subscribe(res => {
      this.loading = false;
      for (let i in res['userDTO'].languages) {
        this.langListNames[i] = res['userDTO'].languages[i].name;
      }
      this.selectedLanguages = res['userDTO'].languages;
      this.star = res['userDTO'].rate;
      this.load = true;
      this.speakerId = res['userDTO'].id;
      this.meetupsService.getSpeakerFutureMeetups(this.speakerId);
      this.meetupFutureSub = this.meetupsService.getSpeakerFutureMeetupUpdateListener()
        .subscribe(meetupsData =>{
        this.speakerFutureMeetups = meetupsData.meetups;
      });
      this.meetupsService.getSpeakerPastMeetups(this.speakerId);
      this.meetupPastSub = this.meetupsService.getSpeakerPastMeetupUpdateListener()
        .subscribe(meetupsData =>{
        this.speakerPastMeetups = meetupsData.meetups;
      });
      this.meetupsService.getUserFutureMeetups();
      this.userMeetupFutureSub = this.meetupsService.getUserFutureMeetupUpdateListener()
        .subscribe(meetupsData =>{
          this.userFutureMeetups = meetupsData.meetups;
        });
      this.meetupsService.getUserPastMeetups().subscribe(meetupsData =>{
        this.userPastMeetups = meetupsData;
      })
      this.badgeList = res['badges'];
      this.changeForm = this.formBuilder.group({
        firstName: [res['userDTO'].firstName, Validators.required],
        lastName: [res['userDTO'].lastName, Validators.required],
        login: [res['userDTO'].login, Validators.required],
        email: [res['userDTO'].email, [Validators.required, Validators.email]],
        about: [res['userDTO'].about],
        languages: ['', Validators.required]
      });
      this.selected = this.selectedLanguages;
      this.firstName = res['userDTO'].firstName;
      this.lastName = res['userDTO'].lastName;
      this.login = res['userDTO'].login;
      this.email = res['userDTO'].email;
      this.about = res['userDTO'].about;
    });
    this.loading = true;
    this.registerService.getLanguages().subscribe(
      res => {
        this.loading = false;
        this.languages = res;
      });

  }

  onEdit() {
    this.edited = true;
  }

  onCancel() {
    this.edited = false;
  }
  onCancelMeetup(id:number){
    this.loading = true;
    this.meetupsService.cancelMeetup(id).subscribe(res => {
      this.loading = false;
      this.meetupsService.getSpeakerFutureMeetups(this.speakerId);
    });
  }
  onTerminate(id:number){
    this.loading = true;
    this.meetupsService.terminateMeetup(id).subscribe(res => {
      this.loading = false;
      this.meetupsService.getSpeakerPastMeetups(this.speakerId);
    });

  }
  onStart(id:number){
    this.loading = true;
    this.meetupsService.startMeetup(id).subscribe(res => {
      this.loading = false;
      this.meetupsService.getSpeakerFutureMeetups(this.speakerId);
    });
  }
  onLeave(id:number){
      this.loading = true;
      this.meetupsService.leaveMeetup(id).subscribe(res => {
        this.loading = false;
        this.meetupsService.getUserFutureMeetups();
      });
  }
}
