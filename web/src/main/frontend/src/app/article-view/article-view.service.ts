import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {ArticleDTO} from "../models/article-dto";

@Injectable({
  providedIn: 'root'
})

export class ArticleViewService {
  //TODO with id
// - Type: GET
// - /api/v1/user/articles/{id}
  private articleURL = '/api/v1/user/articles/9';

  constructor(private http: HttpClient, private router: Router) {
  }

  getArticleDTO(): Observable<ArticleDTO> {
    return this.http.get<ArticleDTO>(this.articleURL).pipe(map((response: Response) => response));
  }

}
