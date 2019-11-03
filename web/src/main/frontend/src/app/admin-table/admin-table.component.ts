import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {AdminTableService} from "./admin-table.service";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  styleUrls: ['./admin-table.component.scss']
})
export class AdminTableComponent implements OnInit {
  public ELEMENT_DATA: UsersToAdmin[] = [];
  displayedColumns: string[] = ['id', 'login', 'email', 'firstName', 'lastName', 'complaint', 'deactivate'];
  dataSource;

  constructor(public adminService: AdminTableService) {
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  ngOnInit() {
    this.adminService.getAllSpeakers().subscribe(res => {
      for (let element in res) {
        this.ELEMENT_DATA[element] = {
          id: res[element]['id'],
          login: res[element]['login'],
          email: res[element]['email'],
          firstName: res[element]['firstName'],
          lastName: res[element]['lastName'],
          complaint: res[element]['complaint']
        };
      }
      this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  deactivate() {

  }
}

export interface UsersToAdmin {
  id: number;
  login: string;
  email: string;
  firstName: string;
  lastName: string;
  complaint: number;
}
