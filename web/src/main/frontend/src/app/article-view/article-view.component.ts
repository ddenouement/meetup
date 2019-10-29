import { Component, OnInit } from '@angular/core';
import {ArticleViewService} from "./article-view.service";

@Component({
  selector: 'app-article-view',
  templateUrl: './article-view.component.html',
  styleUrls: ['./article-view.component.scss']
})
export class ArticleViewComponent implements OnInit {

  constructor(
    public articleViewService: ArticleViewService,
  ) {
  }

  ngOnInit() {
    this.articleViewService.getArticleDTO()
    .subscribe(
      topics => {
        //this.articleViewService = topics;
      },
      err => {
        console.log(err);
      });
  }

}
