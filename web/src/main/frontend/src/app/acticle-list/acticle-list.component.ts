import { Component, OnInit } from '@angular/core';
import {ArticleDTO} from "../models/article-dto";
import {Subscription} from "rxjs";
import {ArticlesService} from "../services/articles.service";

@Component({
  selector: 'app-acticle-list',
  templateUrl: './acticle-list.component.html',
  styleUrls: ['./acticle-list.component.scss']
})
export class ActicleListComponent implements OnInit {

  articles : ArticleDTO[] = [];
  private articlesSub: Subscription;

  constructor(public articlesService: ArticlesService){}

  ngOnInit(): void {
    this.articlesService.getArticles();

    //set up listener to subject
    this.articlesSub = this.articlesService.getArticlesUpdateListener()
      .subscribe((articlesData: { articles: ArticleDTO[] })=>{
        this.articles = articlesData.articles;
      });
  }

  //remove subscription and prevent memory leaks
  ngOnDestroy(): void {
    this.articlesSub.unsubscribe();
  }


}
