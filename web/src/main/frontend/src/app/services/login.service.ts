import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LoginComponent} from "../login/login.component";
import {User} from "../models/user";
import {Authentificationrequest} from "../models/authentificationrequest";

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private logoutURL = '/api/v1/user/logout';
  private logInURL = '/api/v1/user/login';
  private userURL = '/api/v1/user/profile';
  public _logInUser = false;

  constructor(private http: HttpClient) {
  }

  login(user: Authentificationrequest){
    return this.http.post(this.logInURL, user);
  }

  getUser(){
    return this.http.get(this.userURL);
  }

  logInUser(){
    return this._logInUser;
  }

  logoutUser() {
    this.http.get(this.logoutURL);
      this._logInUser = false;
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  }

  set logInUserBool(logInUser: boolean) {
    this._logInUser = logInUser;
  }

  get logInUserBool() {
    return this._logInUser;
  }
}
