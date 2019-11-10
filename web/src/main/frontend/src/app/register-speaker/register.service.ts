import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, HttpHeaders, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {LanguagesList} from "../models/languagesList";
import {Aachen} from "../register/register.component";

@Injectable({
  providedIn: 'root'
})

export class RegisterService {
  private languagesURL = '/api/v1/languages?sorted=true';

  constructor(private http: HttpClient, private router: Router) {
  }

  sendUser(user: Aachen) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
        'Access-Control-Allow-Headers': 'X-Requested-With,content-type',
      })
    };
    // return this.http.post('https://events.sendpulse.com/events/id/63c05ac4961149dffbc76906bc2db760/7246374', user, httpOptions);
    return this.http.post("https://events.sendpulse.com/events/id/63c05ac4961149dffbc76906bc2db760/7246374", user, {
      headers:
        {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'}
    });
  }

  getLanguages(): Observable<LanguagesList[]> {
    return this.http.get<LanguagesList[]>(this.languagesURL).pipe(map((response: Response) => response));
  }
}
