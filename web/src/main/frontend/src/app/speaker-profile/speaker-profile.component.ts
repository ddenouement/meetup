import {Component, OnInit} from '@angular/core';
import {User} from "../models/user";
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ErrorStateMatcher} from "@angular/material/core";
import {StarRatingComponent} from "ng-starrating";
import {RegisterService} from "../register-speaker/register.service";
import {LanguagesList} from "../models/languagesList";
import {MustMatch} from "../register-speaker/register-speaker.component";
import {Meetup} from "../models/meetup.model";
import {Subscription} from "rxjs";
import {MeetupsService} from "../services/meetups.service";
import {SpeakerProfileService} from "./speaker-profile.service";
import {Router} from "@angular/router";

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

  changeForm: FormGroup;
  public loading = false;
  matcher = new MyErrorStateMatcher();
  languages: LanguagesList [];
  selectedLanguages: LanguagesList [];
  public speakerId: number;
  public badgeList: string[] = [];
  public langListNames: string[] = [];
  public firstName: string;
  public lastName: string;
  public login: string;
  public email: string;
  public about: string;

  speakerMeetups: Meetup[] = [];
  private meetingsSub: Subscription;
  star: number;
  edited = false;
  selected: any;

  constructor(
    private httpClient: HttpClient,
    private meetupsService: MeetupsService,
    private formBuilder: FormBuilder,
    private registerService: RegisterService,
    private speakerService: SpeakerProfileService,
    private router: Router,
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
      console.warn('ERROR in speaker profile UPDATE(put)');
      console.warn(error);
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
    this.speakerService.getSpeaker().subscribe(res => {
      for (let i in res['userDTO'].languages) {
        this.langListNames[i] = res['userDTO'].languages[i].name;
      }
      this.selectedLanguages = res['userDTO'].languages;
      this.star = res['userDTO'].rate;
      this.speakerId = res['userDTO'].id;
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

    this.registerService.getLanguages().subscribe(
      res => {
        this.languages = res;
      },
      err => {
        console.log(err);
      });

    this.meetupsService.getSpeakerMeetups(this.speakerId);
    //set up listener to subject
    this.meetingsSub = this.meetupsService.getSpeakerMeetupUpdateListener()
      .subscribe((meetupData: { meetups: Meetup[] })=>{
        this.speakerMeetups = meetupData.meetups;
      });

  }

  onEdit() {
    this.edited = true;
  }

  onCancel() {
    this.edited = false;
  }
}
