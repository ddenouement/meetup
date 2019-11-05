import { Injectable } from "@angular/core";

// @ts-ignore
import {HttpClient, HttpHeaders, Responce, Response} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import { map } from "rxjs/operators";
import { Router } from "@angular/router";

import { Meetup } from "./../models/meetup.model";
import DateTimeFormat = Intl.DateTimeFormat;
import {User} from "../models/user";
import {Language} from "../models/language";
import {TopicClass} from "../models/topic_class";
import {LanguagesList} from "../models/languagesList";

@Injectable({
  providedIn: 'root'
})

export class MeetupsService {
  private meetups: Meetup[] = [];
  private languages: LanguagesList[] = [];
  private speakerMeetups: Meetup[] = [];
  private meetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private languagesUpdated = new Subject<{ languages: LanguagesList[] }>();
  private speakerMeetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private languagesURL = 'http://localhost:9990/api/v1/languages?sorted=true';

  constructor(private http: HttpClient, private router: Router) {
  }

  getMeetupUpdateListener() {
    return this.meetupsUpdated.asObservable();
  }
  getSpeakerMeetupUpdateListener() {
    return this.speakerMeetupsUpdated.asObservable();
  }
  getLanguageUpdateListener() {
    return this.languagesUpdated.asObservable();
  }

  addMeetup(language: Language,
            title: string,
            startDate: Date,
            durationMinutes: number,
            minAttendees: number,
            maxAttendees: number,
            description: string,
            topics: TopicClass[] ){
    const meetup = {
      title: title,
      language: language,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description,
      topics: topics
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
               language: Language,
               state: string,
               startDate: DateTimeFormat,
               durationMinutes: number,
               minAttendees: number,
               maxAttendees: number,
               description: string,
               topics: TopicClass[]){
    const meetup = {
      id: id,
      title: title,
      language: language,
      speaker: speaker,
      state: state,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description,
      topics: topics
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

  getMeetups(meetupsPerPage : number, currentPage: number) {
  // getMeetups() {
    const queryParams = `?pagesize=${meetupsPerPage}&page=${currentPage}`;
    this.http
      .get<Meetup[]>(
        "/api/v1/meetups"+queryParams
      )
      .pipe(
        map(meetupData => {
          return {
            meetups: meetupData.map( meetup => {
                return {
                  id: meetup.id,
                  title: meetup.title,
                  speaker: meetup.speaker,
                  language: meetup.language,
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
        "/api/v1/meetups/speakers/"+id)
      .pipe(
        map(meetupData => {
          return {
            speakerMeetups: meetupData.map( meetup => {
                return {
                  id: meetup.id,
                  title: meetup.title,
                  speaker: meetup.speaker,
                  language: meetup.language,
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

  getLanguages() {
    this.http
      .get<LanguagesList[]>(
        this.languagesURL
      )
      .pipe(
        map(langData => {
          return {
            languages: langData.map( lang => {
                return {
                  id: lang.id,
                  name: lang.name
                }
              }
            )
          };
        })
      )
      .subscribe(transformedLangData => {
        this.languages = transformedLangData.languages;
        this.languagesUpdated.next({
          languages: [...this.languages]
        });
      });
  }

}
