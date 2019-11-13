import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {AdminTableService} from "./admin-table.service";
import {UsersToAdmin} from "../models/userToAdmin";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {MeetupDto} from "../models/meetupDto.model";
import {UserComplaintsDto} from "../models/userComplaintsDto.model";

import { MatSnackBar } from "@angular/material";
import {Registration} from "../models/registration";

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  styleUrls: ['./admin-table.component.scss']
})
export class AdminTableComponent implements OnInit {
  public ELEMENT_DATA: UsersToAdmin[] = [];
  displayedColumns: string[] = ['id', 'login', 'email', 'firstName', 'lastName', 'complaint', 'deactivate'];
  dataSource;
  loading= false;
  users : UserComplaintsDto[] = [];
  totalUsers: number;
  usersPerPage = 5;
  currentPage = 1;
  pageSizeOptions = [5,10,20,30];


  constructor(public snackBar:MatSnackBar, public adminService: AdminTableService) {
  }

  ngOnInit() {
    this.adminService.getUsers(this.usersPerPage, this.currentPage).subscribe(usersData=>
    {
      this.users = usersData.users;
      this.totalUsers = usersData.usersCount;
      this.dataSource = this.users;
    });
  }
  onChangePage(pageData: PageEvent){
    this.currentPage = pageData.pageIndex + 1;
    this.usersPerPage = pageData.pageSize;
    this.loading = true;
    this.adminService.getUsers(this.usersPerPage, this.currentPage).subscribe(usersData=>
    {
      this.loading=false;
      this.users = usersData.users;
      this.totalUsers = usersData.usersCount;
      this.dataSource = this.users;
    });
  }
  onDeactivate(id: number) {
    this.adminService.deactivateUser(id).subscribe(res=> {
      this.ngOnInit();
      let ref =   this.snackBar.open('User deactivated','', {
        duration: 3000
      });

    })
   }

  onActivate(id: number) {
    this.adminService.activateUser(id).subscribe(res=>{
      this.ngOnInit();

    let ref =   this.snackBar.open('User activated','', {
        duration: 3000
      });

    });
  }
}




