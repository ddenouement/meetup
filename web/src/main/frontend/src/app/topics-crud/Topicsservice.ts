import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
// @ts-ignore
import { Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {TopicClass} from "../models/topic_class";


@Injectable()
export class Topicsservice {
  constructor(private http: HttpClient) {
  }

  private topicsURL = '/api/v1/languages?sorted=true';
  getTopics(): Observable<TopicClass[]>{
    return this.http.get<TopicClass[]>(this.topicsURL).pipe(map((response:Response)=>response));
  }
  createTopic(l: TopicClass){
    return this.http.post(this.topicsURL, l);
  }
  updateTopic(id: number, l: TopicClass) {
    const urlParams = new HttpParams().set("id", id.toString());
    return this.http.put(this.topicsURL, l, { params: urlParams});
  }
  deleteTopic(id: number){
    return this.http.delete(this.topicsURL + '/' + id);
  }
}

