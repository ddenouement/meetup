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
import {ArticleDTO} from "../models/article-dto";

@Injectable({
  providedIn: 'root'
})

export class MeetupsService {
  private speakerFutureMeetups: Meetup[] = [];
  private speakerFutureMeetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private speakerPastMeetups: Meetup[] = [];
  private speakerPastMeetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private userFutureMeetups: Meetup[] = [];
  private userFutureMeetupsUpdated = new Subject<{ meetups: Meetup[] }>();
  private joinedCountUpdated = new Subject<number>();

  // private speakerLanguagesURL = 'http://localhost:9990/api/v1/user/languages';
  // private topicsURL = 'http://localhost:9990/api/v1/meetups/topics';
  // private meetupUrl = "http://localhost:9990/api/v1/meetups/";
  // private addMeetupUrl = "http://localhost:9990/api/v1/user/speaker/meetups";
  // private meetupsUrl = "http://localhost:9990/api/v1/meetups";
  // private joinUrl = "http://localhost:9990/api/v1/user/meetups/";
  // private userUrl = "http://localhost:9990/api/v1/user/id";
  // private speakerMeetupsUrl = "http://localhost:9990/api/v1/meetups/speakers/";

  private speakerLanguagesURL = '/api/v1/user/languages';
  private topicsURL = '/api/v1/meetups/topics';
  private meetupUrl = "/api/v1/meetups/";
  private addMeetupUrl = "/api/v1/user/speaker/meetups";
  private meetupsUrl = "/api/v1/user/meetups";
  private joinUrl = "/api/v1/user/meetups/";
  private joinedCountUrl = "api/v1/meetups/users/";
  private speakerMeetupsUrl = "/api/v1/meetups/speakers/";
  private meetupId : number;
  private terminateUrl = "api/v1/user/speaker/meetups/"+this.meetupId+"/terminate";
  constructor(private http: HttpClient, private router: Router) {
  }
  getMeetupJoinedUpdateListener() {
    return  this.joinedCountUpdated.asObservable();
  }

  getSpeakerFutureMeetupUpdateListener() {
    return this.speakerFutureMeetupsUpdated.asObservable();
  }
  getSpeakerPastMeetupUpdateListener() {
    return this.speakerPastMeetupsUpdated.asObservable();
  }
 getUserFutureMeetupUpdateListener() {
    return this.userFutureMeetupsUpdated.asObservable();
  }


  addMeetup(languageId: number,
            topicId: number,
            title: string,
            startDate: Date,
            durationMinutes: number,
            minAttendees: number,
            maxAttendees: number,
            description: string){
    const meetup = {
      title: title,
      languageId: languageId,
      topicId: topicId,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description
    };
    this.http.post<{ meetup: Meetup }>(
        this.addMeetupUrl,meetup)
      .subscribe(responseData => {
        this.router.navigate(["/"]);
      });
  }
  updateMeetup(id: number,
               title: string,
               speakerId: number,
               languageId: number,
               topicId: number,
               state: string,
               startDate: Date,
               durationMinutes: number,
               minAttendees: number,
               maxAttendees: number,
               description: string){
    const meetup = {
      id: id,
      title: title,
      languageId: languageId,
      topicId: topicId,
      speakerId: speakerId,
      state: state,
      startDate: startDate,
      durationMinutes: durationMinutes,
      minAttendees: minAttendees,
      maxAttendees: maxAttendees,
      description: description
    };
    this.http.put(this.addMeetupUrl+"/"+ id, meetup)
      .subscribe(responce => {

      });
  }
  getMeetups(meetupsPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${meetupsPerPage}&page=${currentPage}`;
    return this.http
      .get<{meetups: MeetupDto[], meetupCount : number}>(
        this.meetupUrl+queryParams
      );
  }
  getSpeakerMeetupsPaged(meetupsPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${meetupsPerPage}&page=${currentPage}`;
    return this.http
      .get<{meetups: MeetupDto[], meetupCount : number}>(
        this.speakerMeetupsUrl+queryParams
      );
  }
  getSpeakerFutureMeetups(id: number) {
    this.http
      .get<Meetup[]>(
        this.speakerMeetupsUrl+id+"/future")
      .pipe(
        map(meetupData => {
          return {
            speakerMeetups: meetupData
          };
        })
      )
      .subscribe(transformedMeetupData => {
        this.speakerFutureMeetups = transformedMeetupData.speakerMeetups;
        this.speakerFutureMeetupsUpdated.next({
          meetups: [...this.speakerFutureMeetups]
        });
      });
  }
  getSpeakerPastMeetups(id: number) {
    this.http
      .get<Meetup[]>(
        this.speakerMeetupsUrl+id+"/past")
      .pipe(
        map(meetupData => {
          return {
            speakerMeetups: meetupData
          };
        })
      )
      .subscribe(transformedMeetupData => {
        this.speakerPastMeetups = transformedMeetupData.speakerMeetups;
        this.speakerPastMeetupsUpdated.next({
          meetups: [...this.speakerPastMeetups]
        });
      });
  }
  getUserFutureMeetups() {
    this.http
      .get<Meetup[]>(
        this.meetupsUrl+"/future")
      .pipe(
        map(meetupData => {
          return {
            meetups: meetupData
          };
        })
      )
      .subscribe(transformedMeetupData => {
        this.userFutureMeetups = transformedMeetupData.meetups;
        this.userFutureMeetupsUpdated.next({
          meetups: [...this.userFutureMeetups]
        });
      });
  }
  getUserPastMeetups() {
    return this.http.get<Meetup[]>(this.meetupsUrl+"/past");

  }


  getMeetup(id:number){
    return this.http.get<{meetup:MeetupDto, ifJoinedMeetup: boolean}>(this.meetupUrl+ id);
  }

  getLanguages() {
    return this.http.get<LanguagesList[]>(this.speakerLanguagesURL);
  }
  getTopics() {
    return this.http.get<Topic[]>(this.topicsURL);
  }

  joinMeetup(id:number){
    // @ts-ignore
   return this.http.post(this.joinUrl + id);

  }

  leaveMeetup(id:number){
    // @ts-ignore
    return this.http.delete(this.joinUrl + id);
  }

  terminateMeetup(id:number){
    this.meetupId = id;
    // @ts-ignore
    return this.http.post(this.terminateUrl);
  }
  getJoinedCount(id:number){
    this.http.get<any>(this.joinedCountUrl+id)
    .subscribe(res => {
      this.joinedCountUpdated.next(res);
    });
  }


/*
  getMeetups1(meetupsPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${meetupsPerPage}&page=${currentPage}`;
    this.http
      .get<{meetups: MeetupDto[], meetupCount : number}>(
        this.meetupsUrl+queryParams
      )
      .pipe(
        map(meetupData => {
          return {
            meetupsDto: meetupData.meetups.map( meetup => {
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
                  topic: meetup.topic
                }
              }
            ),
            meetupCount: meetupData.meetupCount
          };
        })
      )
      .subscribe(
        transformedMeetupData => {
          this.meetupsDto = transformedMeetupData.meetupsDto;
          this.meetupsDtoUpdated.next({
            meetups: [...this.meetupsDto],
            meetupCount: transformedMeetupData.meetupCount
          });
        }
      );
  }


  getSpeakerMeetups1(id: number) {
    this.http
      .get<MeetupDto[]>(
        this.speakerMeetupsUrl+id)
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
                  topic: meetup.topic
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
*/


}
