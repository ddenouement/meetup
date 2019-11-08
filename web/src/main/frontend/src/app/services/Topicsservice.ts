import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
// @ts-ignore
import { Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {TopicClass} from "../models/topic_class";
import {Language} from "../models/language";


@Injectable({
  providedIn: 'root'
})
export class Topicsservice {
  constructor(private http: HttpClient) {
  }

  private topicsURL = '/api/v1/meetups/topics';
  getTopics(): Observable<TopicClass[]>{
    return this.http.get<TopicClass[]>(this.topicsURL).pipe(map((response:Response)=>response));
  }
  createTopic(l: TopicClass){
    return this.http.post<TopicClass>(this.topicsURL, l).pipe(map((response:Response) =>response));
  }
  updateTopic(id: number, l: TopicClass) {
    return this.http.put<TopicClass>(this.topicsURL+'/'+id, l).pipe(map((response:Response) =>response));;//, { params: urlParams});
  }
  deleteTopic(id: number){
    return this.http.delete(this.topicsURL + '/' + id);
  }
}

