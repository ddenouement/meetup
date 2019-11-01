import { Injectable } from "@angular/core";

// @ts-ignore
import {HttpClient} from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class SpeakerProfileToUsersService {
  private userURL = '/api/v1/user/people/profile/';
  constructor(private http: HttpClient) {
  }


  getSpeaker(id:number){
    return this.http.get(this.userURL + id);
  }
}
