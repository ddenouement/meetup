import { Component, OnInit } from '@angular/core';
import {ArticleViewService} from "./article-view.service";
import {ArticleDTO} from "../models/article-dto";
import {Validators} from "@angular/forms";

@Component({
  selector: 'app-article-view',
  templateUrl: './article-view.component.html',
  styleUrls: ['./article-view.component.scss']
})
export class ArticleViewComponent implements OnInit {

  articleDTO: ArticleDTO;

  Title: string;
  Content: string;

  constructor(
    public articleViewService: ArticleViewService,

  ) {
  }

  ngOnInit() {
    this.articleViewService.getArticleDTO()
    .subscribe(
      article => {
        this.articleDTO = article;
        this.Title = this.articleDTO.title;
        this.Content = this.articleDTO.content;
        //TODO other values
        console.log(this.articleDTO)
      },
      err => {
        console.log(err);
      });


  }

}
