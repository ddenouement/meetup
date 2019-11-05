import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Badge} from "../models/badge";

@Injectable({
  providedIn: 'root'
})

export class AdminBagesService {
  private badgesURL = '/api/v1/badges';
  private testScriptURL ='/api/v1/badge/check';
  private updateURL = '/api/v1/badges/';
  constructor(private http: HttpClient) {
  }

  createBadge(badge:Badge){
    return this.http.post(this.updateURL, badge);
  }

  updateBadge(id:number, badge:Badge){
    return this.http.put(this.updateURL+id, badge);
  }

  testScript(script: string){
    return this.http.post(this.testScriptURL,script);
  }

  getBadges(){
    return this.http.get(this.badgesURL);
  }
}
