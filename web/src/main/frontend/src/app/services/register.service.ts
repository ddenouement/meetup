import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {LanguagesList} from "../models/languagesList";
import {Registration} from "../models/registration";
import {User} from "../models/user";
import {ChangePass} from "../models/changePass";

@Injectable({
  providedIn: 'root'
})

export class RegisterService {
  private languagesURL = '/api/v1/languages?sorted=true';
  private sendMailURL = '/api/v1/user/welcome';
  private forgotPassURL = '/api/v1/user/password';
  private registerListenerURL = '/api/v1/user/register/listener';
  private registerSpeakerURL = '/api/v1/user/register/speaker';
  private changePassURL = '/api/v1/user/password/change';


  constructor(private http: HttpClient) {
  }

  forgotPassword(mail: String) {
    return this.http.post(this.forgotPassURL, mail);
  }

  sendUser(user: Registration) {
    return this.http.post(this.sendMailURL, user);
  }

  getLanguages(): Observable<LanguagesList[]> {
    return this.http.get<LanguagesList[]>(this.languagesURL).pipe(map((response: Response) => response));
  }

  registerListener(user: User) {
    return this.http.post(this.registerListenerURL, user);
  }

  registerSpeaker(user: User) {
    return this.http.post(this.registerSpeakerURL, user);
  }

  changePassword(pass: ChangePass) {
    return this.http.post(this.changePassURL, pass);
  }
}
