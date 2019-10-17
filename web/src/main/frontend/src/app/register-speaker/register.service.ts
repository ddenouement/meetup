import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {LanguagesList} from "../models/languagesList";



@Injectable({
  providedIn: 'root'
})

export class RegisterService {
  private languagesURL = '/api/v1/languages';
  // private languages: LanguagesList [] = [];
  // private languagesUpdated = new Subject<{ languages: LanguagesList[] }>();

  constructor(private http: HttpClient, private router: Router) {
  }

  getLanguages(): Observable<LanguagesList[]>{
    return this.http.get<LanguagesList[]>(this.languagesURL);
  }


  // getMeetupUpdateListener() {
  //   return this.languagesUpdated.asObservable();
  // }
  //
  // getLanguages() {
  //   this.http
  //     .get<{ message: string; languages: any }>(
  //       this.languagesURL
  //     )
  //     .pipe(
  //       map(data => {
  //         return {
  //           languages: data.languages.map(req => {
  //               return {
  //                 id: req.id,
  //                 name: req.name,
  //               }
  //             }
  //           )
  //         };
  //       })
  //     )
  //     .subscribe(transformedData => {
  //       this.languages = transformedData.languages;
  //       this.languagesUpdated.next({
  //         languages: [...this.languages]
  //       });
  //     });
  // }
}
