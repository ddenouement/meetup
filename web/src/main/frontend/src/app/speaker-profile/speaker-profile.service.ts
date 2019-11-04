import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})

export class SpeakerProfileService {
  private updateUserURL = '/api/v1/user/profile';
  private currUserURL = '/api/v1/user/profile';
  private userURL = '/api/v1/user/profile';
  constructor(private http: HttpClient) {
  }

  updateUser(user: User){
    return this.http.put(this.updateUserURL, user);
  }


  getSpeaker(){
    return this.http.get(this.userURL);
  }
}
