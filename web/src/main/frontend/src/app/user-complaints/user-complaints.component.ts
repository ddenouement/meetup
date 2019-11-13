import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Complaint} from "../models/complaint";

@Component({
  selector: 'app-user-complaints',
  templateUrl: './user-complaints.component.html',
  styleUrls: ['./user-complaints.component.scss']
})
export class UserComplaintsComponent implements OnInit {

  userComplaints : Complaint[];
  userId: number;
  constructor( private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap)=>{
      if(paramMap.has('userId')){
        this.userId = +paramMap.get('userId');
        this.userService.getComplaintsToUser(this.userId).subscribe(complaints =>{
          this.userComplaints = complaints;
        })
      }
      });
  }

}
