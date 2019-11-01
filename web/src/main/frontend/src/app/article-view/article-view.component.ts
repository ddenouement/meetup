import { Component, OnInit } from '@angular/core';
import {ArticleViewService} from "./article-view.service";
import {ArticleDTO} from "../models/article-dto";
import {Validators} from "@angular/forms";
import {Topic} from "../models/topic";
import {User} from "../models/user";

@Component({
  selector: 'app-article-view',
  templateUrl: './article-view.component.html',
  styleUrls: ['./article-view.component.scss']
})
export class ArticleViewComponent implements OnInit {

  articleDTO: ArticleDTO;

  title: string;
  content: string;
  author: User;
  dateTimePosted: string;
  topics: Topic[];

  constructor(
    public articleViewService: ArticleViewService,

  ) {
  }

  ngOnInit() {
    this.articleViewService.getArticleDTO()
    .subscribe(
      article => {
        this.articleDTO = article;
        this.title = this.articleDTO.title;
        this.content = this.articleDTO.content;
        this.author = this.articleDTO.author;
        this.dateTimePosted = this.articleDTO.dateTimePosted;
        this.topics = this.articleDTO.topics;
        console.log(this.topics);
      },
      err => {
        console.log(err);
      });


  }

}
