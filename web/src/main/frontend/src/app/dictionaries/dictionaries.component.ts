
import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'my-dictionaries',
  templateUrl: './dictionaries.component.html',
  styleUrls: ['./dictionaries.component.scss']
})
export class DictionariesComponent implements OnInit {
  public admin = false;
  private userURL = '/api/v1/user/profile';
  constructor(private snackBar:MatSnackBar,private httpClient: HttpClient ) {

  }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe(res => {
        if (res['userDTO'].roles.includes("ADMIN"))
          this.admin = true;
      }
    ,
      error => {
        this.snackBar.open('Only visible to admins!');

      }
    );
  }

}
