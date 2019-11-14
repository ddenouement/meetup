import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
// @ts-ignore
import { Response} from "@angular/common/http";
import {Language} from '../models/language';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class Languagesservice {
  constructor(private http: HttpClient) {
  }

  private languagesURL = '/api/v1/languages';
  private languageURL = '/api/v1/language';

  getLanguages(): Observable<Language[]> {
    return this.http.get<Language[]>(this.languagesURL).pipe(map((response: Response) => response));
  }

  createLanguage(l: Language) {
    return this.http.post<Language>(this.languageURL, l).pipe(map((response:Response) =>response));
  }

  updateLanguage(id:number, l: Language) {
    return this.http.put<Language>(this.languageURL+'/' + id, l).pipe(map((response:Response) =>response));;
  }

  deleteLanguage(id: number) {
    return this.http.delete(this.languageURL + '/' + id);
  }
  getSpeakerLanguages(id: number){
    return this.http.get<Language[]>("/api/v1/user/"+id+"/languages").pipe(map((response: Response) => response));
  }
}

