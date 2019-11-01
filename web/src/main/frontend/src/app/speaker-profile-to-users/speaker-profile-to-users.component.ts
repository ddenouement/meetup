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
  private speakerId: string;

  constructor(public speakerService: SpeakerProfileToUsersService, public route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('speakerId')) {
        this.speakerId = paramMap.get('speakerId');
        this.speakerService.getSpeaker(+this.speakerId).subscribe(res => {
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

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    alert(`Old Value:${$event.oldValue}, 
      New Value: ${$event.newValue}, 
      Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

}
