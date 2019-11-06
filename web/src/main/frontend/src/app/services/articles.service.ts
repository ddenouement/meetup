import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Subject} from "rxjs";
import {Topic} from "../models/topic";
import {User} from "../models/user";
import { ArticleDTO } from '../models/article-dto';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private articles: ArticleDTO[] = [];
  private articlesUpdated = new Subject<{ articles: ArticleDTO[] }>();
  private articlesUrl = "http://localhost:9990/api/v1/user/articles";

  constructor(private http: HttpClient, private router: Router) {
  }
  getArticlesUpdateListener() {
    return this.articlesUpdated.asObservable();
  }



  getArticles(articlesPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${articlesPerPage}&page=${currentPage}`;
    this.http
      .get<ArticleDTO[]>(
        this.articlesUrl + queryParams
      )
      .pipe(
        map(articlesData => {
          return {
            articles: articlesData.map(article => {
                return {
                  id: +article.id,
                  title: article.title,
                  topics: article.topics,
                  content: article.content,
                  dateTimePosted: article.dateTimePosted,
                  author: article.author
              }
              }
            )
          };
        })
      )
      .subscribe(transformedArticlesData => {
        this.articles = transformedArticlesData.articles;
        this.articlesUpdated.next({
          articles: [...this.articles]
        });
      });
  }

}
