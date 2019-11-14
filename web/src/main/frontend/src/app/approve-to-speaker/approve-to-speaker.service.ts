import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {LanguagesList} from "../models/languagesList";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})

export class ApproveToSpeakerService {
  private languagesURL = '/api/v1/languages?sorted=true';
  private userURL = '/api/v1/user/profile';
  private upgradeURL = '/api/v1/users/upgrade';


  constructor(private http: HttpClient) {
  }

  upgradeToSpeaker(user: User){
    return this.http.put(this.upgradeURL, user);
  }

  getUser(){
    return this.http.get(this.userURL);
  }

  getLanguages(): Observable<LanguagesList[]>{
    return this.http.get<LanguagesList[]>(this.languagesURL).pipe(map((response:Response)=>response));
  }
}
