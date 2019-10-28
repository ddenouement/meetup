import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
// @ts-ignore
import { Response} from "@angular/common/http";
import {Language} from '../models/language';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Topic} from "../models/topic";

@Injectable()
export class Dictionariesservice{
  constructor(private http: HttpClient){ }
  private languagesURL = '/api/v1/languages';
  private topicsURL = '/api/v1/languages?sorted=true';

  getLanguages(): Observable<Language[]>{
    return this.http.get<Language[]>(this.languagesURL).pipe(map((response:Response)=>response));
  }
  createLanguage(l: Language){
    return this.http.post(this.languagesURL, l);
  }
  updateLanguage(l: Language) {
    return this.http.put(this.languagesURL, l);
  }
  deleteLanguage(id: number){
    return this.http.delete(this.languagesURL + '/' + id);
  }





  getTopics(): Observable<Topic[]>{
    return this.http.get<Topic[]>(this.topicsURL).pipe(map((response:Response)=>response));
  }
  createTopic(l: Topic){
    return this.http.post(this.topicsURL, l);
  }
  updateTopic(id: number, l: Topic) {
    const urlParams = new HttpParams().set("id", id.toString());
    return this.http.put(this.topicsURL, l, { params: urlParams});
  }
  deleteTopic(id: number){
    return this.http.delete(this.topicsURL + '/' + id);
  }
}
