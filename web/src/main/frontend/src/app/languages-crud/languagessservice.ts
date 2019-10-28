import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
// @ts-ignore
import { Response} from "@angular/common/http";
import {Language} from '../models/language';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class Languagesservice {
  constructor(private http: HttpClient) {
  }

  private languagesURL = '/api/v1/languages';
  private topicsURL = '/api/v1/languages?sorted=true';

  getLanguages(): Observable<Language[]> {
    return this.http.get<Language[]>(this.languagesURL).pipe(map((response: Response) => response));
  }

  createLanguage(l: Language) {
    return this.http.post(this.languagesURL, l);
  }

  updateLanguage(l: Language) {
    return this.http.put(this.languagesURL, l);
  }

  deleteLanguage(id: number) {
    return this.http.delete(this.languagesURL + '/' + id);
  }
}

