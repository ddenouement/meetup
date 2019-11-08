import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LoginComponent} from "./login.component";

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private logoutURL = '/api/v1/user/logout';
  public _logInUser = false;

  constructor(private http: HttpClient) {
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
