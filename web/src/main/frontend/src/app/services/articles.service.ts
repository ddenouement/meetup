import { Injectable } from '@angular/core';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Subject} from "rxjs";
import {Topic} from "../models/topic";
import {User} from "../models/user";
import { ArticleDTO } from '../models/article-dto';
import {UserComplaintsDto} from "../models/userComplaintsDto.model";
import {getTransformedQueryCallExpr} from "@angular/core/schematics/migrations/static-queries/transform";

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private articles: ArticleDTO[] = [];
  private articlesUpdated = new Subject<{articles: ArticleDTO[], articlesCount : number}>();


  // articlesUrl = "/api/v1/user/articles";
  // deleteActicleAdminUrl="/api/v1/user/articles/";
  // //
  deleteActicleAdminUrl="http://localhost:9990/api/v1/user/articles/";
  articlesUrl = "http://localhost:9990/api/v1/user/articles";

  constructor(private http: HttpClient, private router: Router) {
  }

  getArticlesUpdateListener(){
    return this.articlesUpdated.asObservable();
  }

  getArticles(articlesPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${articlesPerPage}&page=${currentPage}`;
    this.http.get<{articles: ArticleDTO[], articlesCount : number}>(
      this.articlesUrl+queryParams
    ).pipe(map (articlesData =>{
      return{
        articles: articlesData.articles,
        articlesCount : articlesData.articlesCount
      };
      })
    ).subscribe(transformedData =>{
      this.articles = transformedData.articles;
      this.articlesUpdated.next({
        articles: [...this.articles],
        articlesCount: transformedData.articlesCount
      })
    })
  }

  deleteArticle(articleId: number){
    return this.http.delete(this.deleteActicleAdminUrl + articleId);

  }


}
