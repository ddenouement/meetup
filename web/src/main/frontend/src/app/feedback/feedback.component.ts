import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StarRatingComponent} from "ng-starrating";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import DateTimeFormat = Intl.DateTimeFormat;
import {Feedback} from "../models/feedback.model";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {error} from "util";

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {

  feedbackForm: FormGroup;
  rate: number;
  feedback: string;
  public enable = false;
  private feedbackURL = '/api/v1/user/rate/meetups/';
  private id: number;
  loading = false;

  constructor(private httpClient: HttpClient,
              private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {

    this.feedbackForm = this.formBuilder.group({
      feedback: [''],
    });
  }

  onSubmit() {
    this.send();
  }

  public send(): void {
    this.loading = true;
    const userFeedback = <Feedback>{
      rate: this.rate,
      feedback: this.feedbackForm.get('feedback').value,
    };
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('meetupId')) {
        this.id = +paramMap.get('meetupId');
        this.httpClient.post(this.feedbackURL+this.id, userFeedback).subscribe(
          res => {
            this.router.navigate(['/']);
          },
          error => {
            this.loading = false;
            console.warn('ERROR in feedback');
            console.warn(error);
          }
        )
      }
    });

  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    this.enable = true;
    this.rate = $event.newValue;
  }

}
