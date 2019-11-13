import { Component, OnInit } from '@angular/core';
import {LanguagesList} from "../models/languagesList";
import {Topic} from "../models/topic";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MeetupsService} from "../services/meetups.service";

@Component({
  selector: 'app-filters-panel',
  templateUrl: './filters-panel.component.html',
  styleUrls: ['./filters-panel.component.scss']
})
export class FiltersPanelComponent implements OnInit {
  languagesList: LanguagesList[];
  topicsList: Topic[];
  form: FormGroup;
  date : Date;
  topic: Topic;
  language: LanguagesList;
  constructor(public meetupService: MeetupsService) { }

  ngOnInit() {
    this.form = new FormGroup({
      language: new FormControl(''),
      topic: new FormControl(''),
      date: new FormControl( null ),
    });
    this.meetupService.getLanguages().subscribe(langData=>{
      this.languagesList = langData;
    });
    this.meetupService.getTopics().subscribe(topicsData=>{
      this.topicsList = topicsData;
    });
  }


  onApply(){
    this.date = this.form.get('date').value;
    this.topic = this.form.get('topic').value;
    this.language = this.form.get('language').value;

  }
}
