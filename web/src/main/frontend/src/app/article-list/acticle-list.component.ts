import { Component, OnInit } from '@angular/core';
import {ArticleDTO} from "../models/article-dto";
import {ArticlesService} from "../services/articles.service";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-acticle-list',
  templateUrl: './acticle-list.component.html',
  styleUrls: ['./acticle-list.component.scss']
})
export class ActicleListComponent implements OnInit {

  articles : ArticleDTO[] = [];
  isLoading = false;
  totalArticles: number;
  articlesPerPage = 9;
  currentPage = 1;
  pageSizeOptions = [9,12,18,24,36,42];

  constructor(public articlesService: ArticlesService){}

  ngOnInit(): void {
    this.isLoading = true;
    this.articlesService.getArticles(this.articlesPerPage,this.currentPage)
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
    this.articlesService.getArticles(this.articlesPerPage,this.currentPage).subscribe(articlesData=>
    {
      this.isLoading = false;
      this.articles = articlesData.articles;
      this.totalArticles = articlesData.articlesCount;
    });
  }


}
