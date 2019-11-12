import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // private userUrl = "http://localhost:9990/api/v1/user/id";
  // private userLoginUrl = "http://localhost:9990/api/v1/users/current/login";

  private userUrl = "/api/v1/user/id";
  private userLoginUrl = "api/v1/users/current/login";

  constructor(private http: HttpClient, private router: Router) {
  }

  getUserId(){
    return this.http.get<any>(this.userUrl);
  }

  getUserLogin(){
    // @ts-ignore
    return this.http.get<any>(this.userLoginUrl,{responseType: 'text'});
  }

}
