import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {LanguagesList} from "../models/languagesList";

@Injectable({
  providedIn: 'root'
})

export class ApproveToSpeakerService {
  private languagesURL = '/api/v1/languages?sorted=true';
  private userURL = '/api/v1/user/profile';


  constructor(private http: HttpClient) {
  }

  getUser(){
    return  this.http.get(this.userURL);
  }

  getLanguages(): Observable<LanguagesList[]>{
    return this.http.get<LanguagesList[]>(this.languagesURL).pipe(map((response:Response)=>response));
  }
}
