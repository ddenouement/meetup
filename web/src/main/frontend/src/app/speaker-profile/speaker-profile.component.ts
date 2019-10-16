import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-speaker-profile',
  templateUrl: './speaker-profile.component.html',
  styleUrls: ['./speaker-profile.component.css']
})
export class SpeakerProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    //profile content swap
    const personal = document.querySelector(".headline__personal");
    const account = document.querySelector(".headline__account");
    const user__personal = document.querySelector(".user__personal");
    const user__account = document.querySelector(".user__account");
    const underline__personal = document.querySelector(".underline__personal");
    const underline__account = document.querySelector(".underline__account");
// On click
    personal.addEventListener("click", function () {
      // Toggle class "is-active"
      if(user__account.classList.contains("account-active")){
        user__account.classList.remove("account-active");
        user__personal.classList.add("personal-active");
        if(!personal.classList.contains("headline__active")){
          personal.classList.add("headline__active");
          account.classList.add("headline__nonact");
          underline__personal.classList.add("underline__active");
          underline__account.classList.add("underline__nonact");

          personal.classList.remove("headline__nonact");
          account.classList.remove("headline__active");
          underline__personal.classList.remove("underline__nonact");
          underline__account.classList.remove("underline__active");
        }
      }
    });
    account.addEventListener("click", function () {
      // Toggle class "is-active"
      if (user__personal.classList.contains("personal-active")) {
        user__personal.classList.remove("personal-active");
        user__account.classList.add("account-active");
        if (!account.classList.contains("headline__active")) {
          account.classList.add("headline__active");
          personal.classList.add("headline__nonact");
          underline__account.classList.add("underline__active");
          underline__personal.classList.add("underline__nonact");

          account.classList.remove("headline__nonact");
          personal.classList.remove("headline__active");
          underline__account.classList.remove("underline__nonact");
          underline__personal.classList.remove("underline__active");
        }
      }
    });

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

}
