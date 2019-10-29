import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {AdminTableService} from "./admin-table.service";

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  styleUrls: ['./admin-table.component.scss']
})
export class AdminTableComponent implements OnInit {
  public ELEMENT_DATA: UsersToAdmin[] = [];
  displayedColumns: string[] = ['id', 'login', 'email', 'firstName', 'lastName'];
  dataSource;


  constructor(public adminService: AdminTableService) {
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  ngOnInit() {
    this.adminService.getAllSpeakers().subscribe(res => {
      for (let element in res) {
        this.ELEMENT_DATA[element] = {
          id: res[element]['id'], login: res[element]['login'], email: res[element]['email'],
          firstName: res[element]['firstName'],
          lastName: res[element]['lastName']
        };
      }
      this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });
  }
}

export interface UsersToAdmin {
  id: number;
  login: string;
  email: string;
  firstName: string;
  lastName: string;
}


