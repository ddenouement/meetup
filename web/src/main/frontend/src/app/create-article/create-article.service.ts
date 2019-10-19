import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient,Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {Topic} from "../models/topic";

@Injectable({
  providedIn: 'root'
})

export class CreateArticleService {
  private topicsURL = '/api/v1/meetups/topics';

  constructor(private http: HttpClient, private router: Router) {
  }

  getTopics(): Observable<Topic[]>{
    return this.http.get<Topic[]>(this.topicsURL).pipe(map((response:Response)=>response));
  }

}
