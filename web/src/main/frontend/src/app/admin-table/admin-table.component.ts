import {Component, OnInit} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {AdminTableService} from "./admin-table.service";
import {UserComplaintsDto} from "../models/userComplaintsDto.model";

import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  styleUrls: ['./admin-table.component.scss']
})
export class AdminTableComponent implements OnInit {
  private displayedColumns: string[] = ['id', 'login', 'email', 'firstName', 'lastName', 'complaint', 'deactivate'];
  private dataSource;
  private loading = false;
  private users: UserComplaintsDto[] = [];
  private totalUsers: number;
  private usersPerPage = 5;
  private currentPage = 1;
  private pageSizeOptions = [5, 10, 20, 30];


  constructor(public snackBar: MatSnackBar, public adminService: AdminTableService) {
  }

  ngOnInit() {
    this.adminService.getUsers(this.usersPerPage, this.currentPage).subscribe(usersData => {
      this.users = usersData.users;
      this.totalUsers = usersData.usersCount;
      this.dataSource = this.users;
    });
  }

  onChangePage(pageData: PageEvent) {
    this.currentPage = pageData.pageIndex + 1;
    this.usersPerPage = pageData.pageSize;
    this.loading = true;
    this.adminService.getUsers(this.usersPerPage, this.currentPage).subscribe(usersData => {
      this.loading = false;
      this.users = usersData.users;
      this.totalUsers = usersData.usersCount;
      this.dataSource = this.users;
    });
  }
}




