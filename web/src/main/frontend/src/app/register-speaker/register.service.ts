import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {LanguagesList} from "../models/languagesList";
import {Registration} from "../models/registration";

@Injectable({
  providedIn: 'root'
})

export class RegisterService {
  private languagesURL = '/api/v1/languages?sorted=true';
  private sendMail = '/api/v1/user/welcome';

  constructor(private http: HttpClient) {
  }

  sendUser(user: Registration) {
    return this.http.post(this.sendMail, user);
  }

  getLanguages(): Observable<LanguagesList[]> {
    return this.http.get<LanguagesList[]>(this.languagesURL).pipe(map((response: Response) => response));
  }
}
