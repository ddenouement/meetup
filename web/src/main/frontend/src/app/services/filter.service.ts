import { Injectable } from '@angular/core';
import {Meetup} from "../models/meetup.model";
import {Filter} from "../models/filter.model";
import {HttpClient} from "@angular/common/http";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {map} from "rxjs/operators";
import {Subject} from "rxjs";
import {MeetupDto} from "../models/meetupDto.model";

@Injectable({
  providedIn: 'root'
})
export class FilterService {
  private addFilterUrl = "/api/v1/users/current/filters";
  private searchFilterUrl = "/api/v1/users/search";
  private filters: Filter[];
  private meetups: MeetupDto[];
  private filtersUpdated = new Subject<{ filters: Filter[] }>();
  constructor(private http: HttpClient, public dialog: MatDialog) { }

  getUserFiltersListener() {
    return this.filtersUpdated.asObservable();
  }

  addFilter(name : string,
            id_language : number,
            rate_from : number,
            rate_to: number,
            time_from : Date,
            time_to : Date,
            topic_id : number,
            title_substring: string){
    const filter = {
      name : name,
      id_language : id_language,
      rate_from : rate_from,
      rate_to: rate_to,
      time_from : time_from,
      time_to : time_to,
      topic_id : topic_id,
      title_substring: title_substring
  };
    this.http.post<{ filter: Filter }>(
      this.addFilterUrl,filter);
  }

  getUserFilters() {
    this.http
      .get<Filter[]>(
        this.addFilterUrl)
      .pipe(
        map(filtersData => {
          return {
            filters: filtersData
          };
        })
      )
      .subscribe(transformedMeetupData => {
        this.filters = transformedMeetupData.filters;
        this.filtersUpdated.next({
          filters: [...this.filters]
        });
      });
  }

  getMeetups(filter: Filter) {
    // @ts-ignore
    return this.http.post<MeetupDto[]>(this.searchFilterUrl,filter);
    // .subscribe(res =>{
    //       this.meetups = res;
    // });res
  }



}
