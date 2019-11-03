import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StarRatingComponent} from "ng-starrating";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import DateTimeFormat = Intl.DateTimeFormat;
import {Feedback} from "../models/feedback.model";
import {Router} from "@angular/router";
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
  //TODO remove hardcode
  private feedbackURL = '/api/v1/rate/meetups/{id}';

  constructor(private httpClient: HttpClient,
              private formBuilder: FormBuilder,
              private router: Router) {
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
    const userFeedback = <Feedback>{
      rate: this.rate,
      feedback: this.feedbackForm.get('feedback').value,
    };

    this.httpClient.post(this.feedbackURL, userFeedback).subscribe(
      res=>{
        this.router.navigate(['/speaker-profile']);
      },
      error =>{
        console.warn('ERROR in feedback');
        console.warn(error);
      }
    )
  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    this.enable = true;
    this.rate = $event.newValue;
    alert(`Old Value:${$event.oldValue}, 
      New Value: ${$event.newValue}, 
      Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

}
