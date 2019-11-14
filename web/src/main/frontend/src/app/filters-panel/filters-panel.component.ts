import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LanguagesList} from "../models/languagesList";
import {Topic} from "../models/topic";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MeetupsService} from "../services/meetups.service";
import {Filter} from "../models/filter.model";
import {FilterService} from "../services/filter.service";
import {MatDialog} from "@angular/material/dialog";
import {FilterDialogComponent} from "../filter-dialog/filter-dialog.component";

@Component({
  selector: 'app-filters-panel',
  templateUrl: './filters-panel.component.html',
  styleUrls: ['./filters-panel.component.scss']
})
export class FiltersPanelComponent implements OnInit {
  filtersList: Filter[];
  languagesList: LanguagesList[];
  topicsList: Topic[];
  filterForm: FormGroup;
  dateFrom : Date;
  dateTo : Date;
  topic: Topic;
  title: string;
  rateFrom = 0;
  rateTo = 0;
  language: LanguagesList;
  @Output() filterEvent = new EventEmitter<Filter>();



  constructor(public meetupService: MeetupsService,
              private filterService: FilterService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.filterForm = new FormGroup({
      language: new FormControl(null),
      topic: new FormControl(null),
      date: new FormControl( null ),
      rateFrom: new FormControl( null ),
      rateTo: new FormControl( null ),
      substring: new FormControl( null ),
    });
    this.meetupService.getLanguages().subscribe(langData=>{
      this.languagesList = langData;
    });
    this.meetupService.getTopics().subscribe(topicsData=>{
      this.topicsList = topicsData;
    });
  }

  onApply(){
    this.dateFrom = this.filterForm.get('date').value[0];
    this.dateTo = this.filterForm.get('date').value[1];
    this.topic = this.filterForm.get('topic').value;
    this.language = this.filterForm.get('language').value;
    this.rateFrom =  this.filterForm.get('rateFrom').value;
    this.rateTo =  this.filterForm.get('rateTo').value;
    this.rateTo =  this.filterForm.get('rateTo').value;
    const filter = {
      id: null,
      id_user: null,
      name: null,
      id_language : this.language.id,
      rate_from : this.rateFrom,
      rate_to: this.rateTo,
      time_from : this.dateFrom,
      time_to : this.dateTo,
      topic_id : this.topic.id,
      title_substring: this.filterForm.value.substring
    };
    this.filterEvent.emit(filter);

  }
  onSave(){
     this.filterService.addFilter(
       this.title,
       this.filterForm.value.language.id ? null : this.filterForm.value.language.id,
       this.filterForm.value.rateFrom,
       this.filterForm.value.rateTo,
       this.dateFrom,
       this.dateTo,
       this.filterForm.value.topic.id ? null : this.filterForm.value.topic.id,
      this.filterForm.value.substring
    );
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(FilterDialogComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.title = result;
      this.onSave();
    });
  }

}
