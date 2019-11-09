import {Component, OnInit, Input, forwardRef} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.scss']
})
export class ComplaintComponent implements OnInit {

  _userId:number;
  complaint:string;
  constructor(private snackBar:MatSnackBar,
              private httpClient: HttpClient,
              private route:ActivatedRoute) {
  }

  ngOnInit() {
   this._userId = Number(this.route.snapshot.paramMap.get('id'));
   if(this._userId === 0) {
     this._userId = Number(this.route.snapshot.paramMap.get('speakerId'));
   }
   if (this._userId === 0 ) {
     this._userId = Number(this.route.snapshot.paramMap.get('listenerId'));
   }
  }
  onSubmit() {
    const  complaint_in_request_body = {
      id:0,
      id_user_from: 0,
      id_user_to: this._userId,
      content: this.complaint,
      //in backnd we translate it to Date format
      postedDate: null,
      postedDateInNumFormat: new Date().getTime(),
      isRead:false
    };
     this.httpClient.post("/api/v1/user/complaint", complaint_in_request_body).subscribe(data=>
       {
this.complaint="";

         this.snackBar.open('Complaint sent');
       }
       ,err =>{

         this.snackBar.open('Error!');
       }

     );
  }

}
