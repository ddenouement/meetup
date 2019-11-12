import { Component, OnInit } from '@angular/core';
import {ArticleViewService} from "./article-view.service";
import {ArticleDTO} from "../models/article-dto";
import {Validators} from "@angular/forms";
import {Topic} from "../models/topic";
import {User} from "../models/user";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Meetup} from "../models/meetup.model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-article-view',
  templateUrl: './article-view.component.html',
  styleUrls: ['./article-view.component.scss']
})
export class ArticleViewComponent implements OnInit {

  articleDTO: ArticleDTO;

  title: string;
  content: string;
  isLoading = false;
  isAdmin = false;
  adminLogin = "admin";
  author: User;
  dateTimePosted: string;
  topics: Topic[];
  meetup: Meetup;
  private articleId: string;

  constructor(public articleViewService: ArticleViewService,public userService: UserService, public route: ActivatedRoute ) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('articleId')) {
        this.articleId = paramMap.get('articleId');
        this.isLoading = true;
        this.articleViewService.getArticleDTO(+this.articleId)
          .subscribe(
            article => {
              this.isLoading = false;
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
        this.isLoading = true;
        this.userService.getUserLogin().subscribe(res=>{
          this.isLoading = false;
          if( this.adminLogin === res){
            this.isAdmin = true;
          }
        });
      }
    });



  }

}
