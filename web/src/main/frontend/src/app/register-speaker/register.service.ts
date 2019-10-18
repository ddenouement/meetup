import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient,Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {LanguagesList} from "../models/languagesList";

@Injectable({
  providedIn: 'root'
})

export class RegisterService {
  private languagesURL = '/api/v1/languages?sorted=true';

  constructor(private http: HttpClient, private router: Router) {
  }

  getLanguages(): Observable<LanguagesList[]>{
    return this.http.get<LanguagesList[]>(this.languagesURL).pipe(map((response:Response)=>response));
  }

}
