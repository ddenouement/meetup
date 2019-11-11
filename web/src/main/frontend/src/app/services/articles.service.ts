import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Subject} from "rxjs";
import {Topic} from "../models/topic";
import {User} from "../models/user";
import { ArticleDTO } from '../models/article-dto';
import {UserComplaintsDto} from "../models/userComplaintsDto.model";

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private articles: ArticleDTO[] = [];
  private articlesUpdated = new Subject<{ articles: ArticleDTO[] }>();
  articlesUrl = "/api/v1/user/articles";

  constructor(private http: HttpClient, private router: Router) {
  }
  // getArticles() {
  //   this.http
  //     .get<ArticleDTO[]>(
  //       this.articlesUrl
  //     )
  //     .pipe(
  //       map(articlesData => {
  //         return {
  //           articles: articlesData.map(article => {
  //               return {
  //                 id: +article.id,
  //                 title: article.title,
  //                 topics: article.topics,
  //                 content: article.content,
  //                 dateTimePosted: article.dateTimePosted,
  //                 author: article.author
  //             }
  //             }
  //           )
  //         };
  //       })
  //     )
  //     .subscribe(transformedArticlesData => {
  //       this.articles = transformedArticlesData.articles;
  //       this.articlesUpdated.next({
  //         articles: [...this.articles]
  //       });
  //     });
  // }
  getArticles(articlesPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${articlesPerPage}&page=${currentPage}`;
    return this.http.get<{articles: ArticleDTO[], articlesCount : number}>(
      this.articlesUrl+queryParams
    );
  }
}
