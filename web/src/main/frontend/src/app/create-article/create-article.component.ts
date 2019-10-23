import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Topic} from "../models/topic";
import {CreateArticleService} from "./create-article.service";
import {Article} from "../models/article";


@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {

  createArticleForm: FormGroup;
  articleTopics: Topic [];
  public title: string;
  public content: string;

  constructor(
    public createArticleService: CreateArticleService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  ngOnInit() {
    this.createArticleForm = this.formBuilder.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      articleTopics: ['', Validators.required],
    });

    this.createArticleService.getTopics()
    .subscribe(
      topics => {
        this.articleTopics = topics;
      },
      err => {
        console.log(err);
      });
  }

  onSubmit() {
    this.addArticle();
  }

  public addArticle(): void {
    let topicListIds: number[] = [];
    for (let i in this.createArticleForm.get('articleTopics').value) {
      topicListIds[i] = this.createArticleForm.get('articleTopics').value[i].id;
    }
    const article = <Article>{
      title: this.createArticleForm.get('title').value,
      content: this.createArticleForm.get('content').value,
      topicIds: topicListIds
    };
    this.createArticleForm.controls['title'].disable();
    this.createArticleForm.controls['content'].disable();
    this.createArticleForm.controls['articleTopics'].disable();
    this.httpClient.post("/api/v1/user/speaker/articles", article).subscribe(data => {

      this.router.navigate(['/speaker-profile']);
    });

  }


}
