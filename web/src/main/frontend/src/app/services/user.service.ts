import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Complaint} from "../models/complaint";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // private userUrl = "http://localhost:9990/api/v1/user/id";
  // private userLoginUrl = "http://localhost:9990/api/v1/users/current/login";
  private userUrl = "/api/v1/user/id";
  private userLoginUrl = "api/v1/users/current/login";
  private usersUrl = "api/v1/users/";
  private complaintsUrl = "/complaints";

  constructor(private http: HttpClient, private router: Router) {
  }

  getUserId(){
    return this.http.get<any>(this.userUrl);
  }

  getUserLogin(){
    // @ts-ignore
    return this.http.get<any>(this.userLoginUrl,{responseType: 'text'});
  }

  getComplaintsToUser(userId : number){
    return this.http.get<Complaint[]>(this.usersUrl+userId+this.complaintsUrl);
  }

}
