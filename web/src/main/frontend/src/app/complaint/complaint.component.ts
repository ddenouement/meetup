import {Component, OnInit, Input, forwardRef} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

// <app-complaint   [userLogin]="this.changeForm.get('login').value"    ></app-complaint>
@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.scss']
})
export class ComplaintComponent implements OnInit {
  /*@Input()
  set userId(id: number){
    this._userId = id;
  }*/
  _userId:number;
  complaint:string;
  constructor(
    private httpClient: HttpClient,
    private route:ActivatedRoute) {
  }

  ngOnInit() {
   this._userId = Number(this.route.snapshot.paramMap.get('id'));
  }
  onSubmit() {
    alert(this._userId);
    const  complaint_in_request_body = {
      id:0,
      id_user_from: 0,
      id_user_to: this._userId,
      content: this.complaint,
      //in backnd we translate it to Date dormat
      postedDate: null,
      postedDateInNumFormat: new Date().getTime(),
      isRead:false
    };
   alert(complaint_in_request_body.content);
    alert(complaint_in_request_body.id_user_to);
     this.httpClient.post("/api/v1/user/complaint", complaint_in_request_body).subscribe();
  }

}
