import { Component, OnInit } from '@angular/core';
import {ArticleDTO} from "../models/article-dto";
import {ArticlesService} from "../services/articles.service";
import {PageEvent} from "@angular/material/paginator";
import {Subscription} from "rxjs";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-acticle-list',
  templateUrl: './acticle-list.component.html',
  styleUrls: ['./acticle-list.component.scss']
})
export class ActicleListComponent implements OnInit {

  articles : ArticleDTO[] = [];
  isLoading = false;
  isAdmin = false;
  userLogin : string;
  userId : number;
  adminLogin = "admin";
  totalArticles: number;
  articlesPerPage = 9;
  currentPage = 1;
  pageSizeOptions = [9,12,18,24,36,42];
  private articlesSub: Subscription;

  constructor(public articlesService: ArticlesService, public userService: UserService){}

  ngOnInit(): void {
    this.isLoading = true;
    this.userService.getUserId().subscribe(res=>{
      this.userId = res;
    });

    this.userService.getUserLogin().subscribe(login=>{
      this.isLoading = false;
      this.userLogin = login;
      if( this.adminLogin === this.userLogin){
        this.isAdmin = true;
      }
    });

    this.articlesService.getArticles(this.articlesPerPage,this.currentPage);
    this.articlesSub = this.articlesService.getArticlesUpdateListener()
      .subscribe(articlesData =>{
        this.isLoading = false;
        this.articles = articlesData.articles;
        this.totalArticles = articlesData.articlesCount;
      });

  }
  onChangePage(pageData: PageEvent){
    this.currentPage = pageData.pageIndex + 1;
    this.articlesPerPage = pageData.pageSize;
    this.isLoading = true;
    this.articlesService.getArticles(this.articlesPerPage,this.currentPage);
  }


  onDelete(articleId : number) {
    this.isLoading = true;
    this.articlesService.deleteArticle(articleId).subscribe(()=>{
      this.articlesService.getArticles(this.articlesPerPage,this.currentPage);
    })
  }
}
