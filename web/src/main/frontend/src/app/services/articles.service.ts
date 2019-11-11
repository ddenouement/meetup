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

  // articlesUrl = "/api/v1/user/articles";
  articlesUrl = "http://localhost:9990/api/v1/user/articles";

  constructor(private http: HttpClient, private router: Router) {
  }
  getArticles(articlesPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${articlesPerPage}&page=${currentPage}`;
    return this.http.get<{articles: ArticleDTO[], articlesCount : number}>(
      this.articlesUrl+queryParams
    );
  }
}
