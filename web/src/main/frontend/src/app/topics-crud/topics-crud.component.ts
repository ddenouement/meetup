import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {TopicClass} from "../models/topic_class";
import {Topicsservice} from "../services/Topicsservice";
import {Language} from "../models/language";

@Component({
  selector: 'app-topics-crud',
  templateUrl: './topics-crud.component.html',
  styleUrls: ['./topics-crud.component.scss'],
  providers: [Topicsservice]
})
export class TopicsCrudComponent implements OnInit {
  @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any>;


  editedTopic: TopicClass;
  topics: Array<TopicClass>;
  isNewRecordT: boolean;
  statusMessageT: string;
  constructor(private serv: Topicsservice) {

    this.topics = new Array<TopicClass>();
     this.isNewRecordT=false;

  }

  ngOnInit() {
    this.loadTopics();
  }

  //topics

  private loadTopics() {
    this.serv.getTopics()
      .subscribe(
        topics => {
          this.topics = topics;
        },
        err => {
          console.log(err);
        });
  }
  addTopic() {
    this.editedTopic = new TopicClass(0,"");
    this.topics.unshift(this.editedTopic);
    this.isNewRecordT = true;
  }

  editTopic(t: TopicClass) {
    this.editedTopic = new TopicClass( t.id,t.name);
  }
  loadTemplateT(t: TopicClass) {
    if (this.editedTopic && this.editedTopic.id == t.id) {
      return this.editTopic;
    } else {
      return this.readOnlyTemplate;
    }
  }
  saveTopic() {
    if(this.editedTopic.name.length==0){
      this.statusMessageT='Please enter valid name';
    }
   /* if (this.isNewRecordT) {
      this.serv.createTopic(this.editedTopic).subscribe(data => {

          this.statusMessageT = 'Saved',
            this.topics.unshift(data['topic']);
        },
        err => {
          alert(err);
        });
      this.isNewRecordT = false;
      this.topics.splice(this.topics.indexOf(this.editedTopic), 1),
        this.editedTopic = null;
    }
    else {
      var index = this.topics.findIndex(x => x.id == this.editedTopic.id);
      const nl = <Language>{id: this.editedTopic.id, name:this.editedTopic.name };
      this.serv.updateTopic(nl.id,nl).subscribe(data => {
          this.statusMessageT = 'Edited',
            this.topics[index] =  data['topic'];
        },
        err => {
          alert(err);
        });
      this.editedTopic = null;
    }*/
  }
  cancelT() {
    if (this.isNewRecordT) {
      this.topics.shift();
      this.isNewRecordT = false;
    }
    this.editedTopic = null;
  }
  deleteTopic(t: TopicClass) {
    /*this.serv.deleteTopic(t.id).subscribe(data => {
        this.statusMessageT = 'Deletd',
          this.topics.splice(this.topics.indexOf(t),1);

      },
      err => {
        // alert(err),
        this.statusMessageT = err;
      });*/
  }
}
