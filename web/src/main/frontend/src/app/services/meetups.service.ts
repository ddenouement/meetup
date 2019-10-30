import { Injectable } from "@angular/core";

// @ts-ignore
import {HttpClient, HttpHeaders, Responce} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import { map } from "rxjs/operators";
import { Router } from "@angular/router";

import { Meetup } from "./../models/meetup.model";
import DateTimeFormat = Intl.DateTimeFormat;
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})

export class MeetupsService {
  private meetups: Meetup[] = [];
  private speakerMeetups: Meetup[] = [];
  private meetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private speakerMeetupsUpdated = new Subject<{ meetups: Meetup[] }>();

  constructor(private http: HttpClient, private router: Router) {
  }

  getMeetupUpdateListener() {
    return this.meetupsUpdated.asObservable();
  }
  getSpeakerMeetupUpdateListener() {
    return this.speakerMeetupsUpdated.asObservable();
  }


  addMeetup(title: string,
            startDate: Date,
            durationMinutes: number,
            minAttendees: number,
            maxAttendees: number,
            description: string){
    const meetup = {
      title: title,
      languageId: 1,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description,
      topics: []
    };
    this.http
      .post<{ meetup: Meetup }>(
        "http://localhost:9990/api/v1/user/speaker/meetups",
        meetup,{headers: new HttpHeaders({'Accept':'application/json', 'Content-Type':'application/json'})
        }
      )
      .subscribe(responseData => {
        console.log(responseData);
        this.router.navigate(["/"]);
      });
  }

  updateMeetup(id: number,
               title: string,
               speaker: User,
               state: string,
               startDate: DateTimeFormat,
               durationMinutes: number,
               minAttendees: number,
               maxAttendees: number,
               description: string){
    const meetup = {
      id: id,
      title: title,
      languageId: 1,
      speaker: speaker,
      state: state,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description,
      topics: []
    };
    this.http.put("http://localhost:9990/api/v1/user/speaker/meetups/"+ id, meetup)
      .subscribe(responce => {
        const updatedMeetups = [...this.meetups];
        const oldMeetupIndex = updatedMeetups.findIndex(m => m.id === meetup.id);
        updatedMeetups[oldMeetupIndex] = meetup;
        this.meetups = updatedMeetups;
        this.meetupsUpdated.next({
          meetups: [...this.meetups]
        });
      });
  }

  getMeetups() {
    this.http
      .get<Meetup[]>(
        "http://localhost:9990/api/v1/meetups"
      )
      .pipe(
        map(meetupData => {
          return {
            meetups: meetupData.map( meetup => {
                return {
                  id: meetup.id,
                  title: meetup.title,
                  speaker: meetup.speaker,
                  state: meetup.state,
                  startDate: meetup.startDate,
                  durationMinutes: meetup.durationMinutes,
                  minAttendees: meetup.minAttendees,
                  maxAttendees: meetup.maxAttendees,
                  description: meetup.description,
                  topics: meetup.topics
                }
              }
            )
          };
        })
      )
      .subscribe(transformedMeetupData => {
        this.meetups = transformedMeetupData.meetups;
        this.meetupsUpdated.next({
          meetups: [...this.meetups]
        });
      });
  }
  getSpeakerMeetups(id: number) {
    this.http
      .get<Meetup[]>(
        "http://localhost:9990/api/v1/meetups/speaker/"+id)
      .pipe(
        map(meetupData => {
          return {
            speakerMeetups: meetupData.map( meetup => {
                return {
                  id: meetup.id,
                  title: meetup.title,
                  speaker: meetup.speaker,
                  state: meetup.state,
                  startDate: meetup.startDate,
                  durationMinutes: meetup.durationMinutes,
                  minAttendees: meetup.minAttendees,
                  maxAttendees: meetup.maxAttendees,
                  description: meetup.description,
                  topics: meetup.topics
                }
              }
            )
          };
        })
      )
      .subscribe(transformedMeetupData => {
        this.speakerMeetups = transformedMeetupData.speakerMeetups;
        this.speakerMeetupsUpdated.next({
          meetups: [...this.speakerMeetups]
        });
      });
  }


  getMeetup(id:number){
    return this.http.get<Meetup>("http://localhost:9990/api/v1/meetups/"+ id);
  }


}
