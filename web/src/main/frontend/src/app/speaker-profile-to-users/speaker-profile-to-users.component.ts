import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {StarRatingComponent} from "ng-starrating";

@Component({
  selector: 'app-speaker-profile-to-users',
  templateUrl: './speaker-profile-to-users.component.html',
  styleUrls: ['./speaker-profile-to-users.component.scss']
})
export class SpeakerProfileToUsersComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    alert(`Old Value:${$event.oldValue}, 
      New Value: ${$event.newValue}, 
      Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

}
