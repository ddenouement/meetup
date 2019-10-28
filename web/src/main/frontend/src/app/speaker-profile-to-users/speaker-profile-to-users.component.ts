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


    const hamburger = document.querySelector(".hamburger");
    const bar = document.querySelector(".sidebar");
// On click
    hamburger.addEventListener("click", function () {
      // Toggle class "is-active"
      bar.classList.toggle("active");
      hamburger.classList.toggle("is-active");
      // Do something else, like open/close menu
    });
  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    alert(`Old Value:${$event.oldValue}, 
      New Value: ${$event.newValue}, 
      Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

}
