import { Injectable } from "@angular/core";

// @ts-ignore
import {HttpClient} from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class SpeakerProfileToUsersService {
  private userURL = '/api/v1/user/people/profile/';
  private subscribeURL = '/api/v1/user/speakers/';
  private currUserURL = '/api/v1/user/profile';
  private currURL = '/api/v1/user/profile';
  constructor(private http: HttpClient) {
  }

  getMyProfile(){
    return this.http.get(this.currUserURL);
  }

  allSubscribersURL(id:number){
    return this.http.get(this.subscribeURL + id + '/subscribers');
  }

  unsubscribeTo(id:number){
    return this.http.delete(this.subscribeURL + id + '/subscribe');
  }
  subscribeTo(id:number){
    // @ts-ignore
    return this.http.post(this.subscribeURL + id + '/subscribe');
  }

  getSpeaker(id:number){
    return this.http.get(this.userURL + id);
  }
}
