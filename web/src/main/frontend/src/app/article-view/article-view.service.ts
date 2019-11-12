import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, Response} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";
import {ArticleDTO} from "../models/article-dto";

@Injectable({
  providedIn: 'root'
})

export class ArticleViewService {
  //TODO with id
// - Type: GET
// - /api/v1/user/articles/{id}
  private articleURL = '/api/v1/user/articles/';
  // private articleURL = 'http://localhost:9990/api/v1/user/articles/';

  constructor(private http: HttpClient, private router: Router) {
  }

  getArticleDTO(id:number): Observable<ArticleDTO> {
    return this.http.get<ArticleDTO>(this.articleURL+id).pipe(map((response: Response) => response));
  }

}
