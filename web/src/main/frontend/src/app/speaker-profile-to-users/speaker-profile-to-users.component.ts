import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {StarRatingComponent} from "ng-starrating";
import {LanguagesList} from "../models/languagesList";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {ListenerProfileToUsersService} from "../listener-profile-to-users/listener-profile-to-users.service";
import {SpeakerProfileToUsersService} from "./speaker-profile-to-users.service";

@Component({
  selector: 'app-speaker-profile-to-users',
  templateUrl: './speaker-profile-to-users.component.html',
  styleUrls: ['./speaker-profile-to-users.component.scss']
})
export class SpeakerProfileToUsersComponent implements OnInit {
  languages: LanguagesList [];
  public badgeList: string[] = [];
  public firstName: string;
  public lastName: string;
  public login: string;
  public email: string;
  public about: string;
  private langList: string[] = [];
  private subscribeToIdsList: number[] = [];
  private speakerId: string;
  public star: number;
  subscribe = true;
  unsubscribe = false;
  subscribeText = 'SUBSCRIBE';

  constructor(public speakerService: SpeakerProfileToUsersService, public route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('speakerId')) {
        this.speakerId = paramMap.get('speakerId');
        this.speakerService.getSpeaker(+this.speakerId).subscribe(res => {
          this.speakerService.getMyProfile().subscribe(res => {
            for (let i in res['subscribedTo']) {
              this.subscribeToIdsList[i] = res['subscribedTo'][i].id;
            }
            if(this.subscribeToIdsList.includes(+this.speakerId)){
              this.subscribeText = 'UNSUBSCRIBE';
              this.subscribe = false;
              this.unsubscribe = true;
            }else {
              this.subscribeText = 'SUBSCRIBE';
              this.subscribe = true;
              this.unsubscribe = false;
            }
          });
          this.star = res['userDTO'].rate;
          this.badgeList = res['badges'];
          for (let i in res['userDTO'].languages) {
            this.langList[i] = res['userDTO'].languages[i].name;
          }
          this.firstName = res['userDTO'].firstName;
          this.lastName = res['userDTO'].lastName;
          this.login = res['userDTO'].login;
          this.email = res['userDTO'].email;
          this.about = res['userDTO'].about;
        });
      }
    });

  }

  onSubscribe() {
    if (this.subscribe) {
      this.speakerService.unsubscribeTo(+this.speakerId).subscribe(res => {
        this.subscribe = false;
        this.unsubscribe = true;
        this.subscribeText = 'UNSUBSCRIBE';
      });
    } else {
      this.speakerService.subscribeTo(+this.speakerId).subscribe(res => {
        this.subscribe = true;
        this.unsubscribe = false;
        this.subscribeText = 'SUBSCRIBE';
      });
    }
  }
}
