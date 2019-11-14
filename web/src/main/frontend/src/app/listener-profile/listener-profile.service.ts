import { Injectable } from "@angular/core";

// @ts-ignore
import {HttpClient} from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})

export class ListenerProfileService {
  private userURL = '/api/v1/user/profile';
  constructor(private http: HttpClient) {
  }


  getListener(){
    return this.http.get(this.userURL);
  }

}
