import { Component, OnInit } from '@angular/core';
import {ArticleDTO} from "../models/article-dto";
import {Subscription} from "rxjs";
import {ArticlesService} from "../services/articles.service";
import {PageEvent} from "@angular/material/paginator";
import {MeetupDto} from "../models/meetupDto.model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-acticle-list',
  templateUrl: './acticle-list.component.html',
  styleUrls: ['./acticle-list.component.scss']
})
export class ActicleListComponent implements OnInit {

  articles : ArticleDTO[] = [];
  totalArticles = 30;
  articlesPerPage = 5;
  currentPage = 1;
  pageSizeOptions = [5,10,20,30];
  isLoading = false;
  private articlesSub: Subscription;

  constructor(public articlesService: ArticlesService){}

  ngOnInit(): void {
    this.articlesService.getArticles(this.articlesPerPage, this.currentPage);
    //set up listener to subject
    this.articlesSub = this.articlesService.getArticlesUpdateListener()
      .subscribe((articlesData: { articles: ArticleDTO[] })=>{
        this.articles = articlesData.articles;
      });
  }
  onChangePage(pageData: PageEvent){
    this.currentPage = pageData.pageIndex + 1;
    this.articlesPerPage = pageData.pageSize;
    this.isLoading = true;
    this.articlesService.getArticles(this.articlesPerPage, this.currentPage);
    this.articlesSub = this.articlesService.getArticlesUpdateListener()
      .subscribe((articleData: { articles: ArticleDTO[] })=>{
        this.isLoading=false;
        this.articles = articleData.articles;
      });
  }

  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
    this.articlesSub.unsubscribe();
  }


}
