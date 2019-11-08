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
import {Topic} from "../models/topic";
import {MeetupDto} from "../models/meetupDto.model";

@Injectable({
  providedIn: 'root'
})

export class MeetupsService {
  private meetups: Meetup[] = [];
  private meetupsDto: MeetupDto[] = [];
  private languages: LanguagesList[] = [];
  private topics: Topic[] = [];
  private speakerMeetups: Meetup[] = [];
  private meetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private meetupsDtoUpdated = new Subject<{ meetups: MeetupDto[] }>();
  private languagesUpdated = new Subject<{ languages: LanguagesList[] }>();
  private topicsUpdated = new Subject<{ topics: Topic[] }>();
  private speakerMeetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private languagesURL = 'http://localhost:9990/api/v1/languages?sorted=true';
  private topicsURL = 'http://localhost:9990/api/v1/meetups/topics';
  private meetupUrl = "http://localhost:9990/api/v1/meetups/";
  private addMeetupUrl = "http://localhost:9990/api/v1/user/speaker/meetups";
  private meetupsUrl = "http://localhost:9990/api/v1/meetups";



  constructor(private http: HttpClient, private router: Router) {
  }

  getMeetupUpdateListener() {
    return this.meetupsUpdated.asObservable();
  }
  getMeetupDtoUpdateListener() {
    return this.meetupsDtoUpdated.asObservable();
  }
  getSpeakerMeetupUpdateListener() {
    return this.speakerMeetupsUpdated.asObservable();
  }
  getLanguageUpdateListener() {
    return this.languagesUpdated.asObservable();
  }
  getTopicUpdateListener() {
    return this.topicsUpdated.asObservable();
  }

  addMeetup(languageId: number,
            title: string,
            startDate: Date,
            durationMinutes: number,
            minAttendees: number,
            maxAttendees: number,
            description: string,
            topics: TopicClass[] ){
    const meetup = {
      title: title,
      languageId: languageId,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description,
      topics: topics
    };
    this.http
      .post<{ meetup: Meetup }>(
        this.addMeetupUrl,
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
               languageId: number,
               state: string,
               startDate: Date,
               durationMinutes: number,
               minAttendees: number,
               maxAttendees: number,
               description: string,
               topics: TopicClass[]){
    const meetup = {
      id: id,
      title: title,
      languageId: languageId,
      speaker: speaker,
      state: state,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description,
      topics: topics
    };
    this.http.put(this.addMeetupUrl+"/"+ id, meetup)
      .subscribe(responce => {
        // const updatedMeetups = [...this.meetups];
        // const oldMeetupIndex = updatedMeetups.findIndex(m => m.id === meetup.id);
        // updatedMeetups[oldMeetupIndex] = meetup;
        // this.meetups = updatedMeetups;
        // this.meetupsUpdated.next({
        //   meetups: [...this.meetups]
        // });
        console.log(responce);
      });
  }


  getMeetups(meetupsPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${meetupsPerPage}&page=${currentPage}`;
    this.http
      .get<MeetupDto[]>(
        this.meetupsUrl+queryParams
      )
      .pipe(
        map(meetupData => {
          return {
            meetupsDto: meetupData.map( meetup => {
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
      .subscribe(
        transformedMeetupData => {
        this.meetupsDto = transformedMeetupData.meetupsDto;
        this.meetupsDtoUpdated.next({
          meetups: [...this.meetupsDto]
        });
      }
      );
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
                  languageId: meetup.languageId,
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
    return this.http.get<MeetupDto>(this.meetupUrl+ id);
  }

  getLanguages() {
    this.http.get<LanguagesList[]>(this.languagesURL)
      .pipe(map(langData => {
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
  getTopics() {
    this.http.get<Topic[]>(this.topicsURL)
      .pipe(map(topicsData => {
          return {
            topics: topicsData.map( topic => {
                return {
                  id: topic.id,
                  name: topic.name
                }
              }
            )
          };
        })
      )
      .subscribe(transformedTopicsData => {
        this.topics = transformedTopicsData.topics;
        this.topicsUpdated.next({
          topics: [...this.topics]
        });
      });
  }
}
