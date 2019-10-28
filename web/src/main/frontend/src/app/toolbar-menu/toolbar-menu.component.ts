import {Component, OnInit} from '@angular/core';
import {LoginService} from "../login/login.service";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-toolbar-menu',
  templateUrl: './toolbar-menu.component.html',
  styleUrls: ['./toolbar-menu.component.scss']
})
export class ToolbarMenuComponent implements OnInit {
  private _authService: LoginService;
  private userURL = '/api/v1/user/profile';
  private user;
  private logined = false;


  constructor(authService: LoginService,
              private httpClient: HttpClient,
              private router: Router) {
    this._authService = authService;
  }

  ngOnInit() {
    this.httpClient.get(this.userURL).subscribe(res => {
        this.user = <User>{
          role: res['UserDTO'].role,
        };
        this.logined = true;
      },
      error => {
        console.warn(error);
        this.logined = false;
      });
  }

  landingPage() {
    if (this.logined) {
      this.router.navigate(['/404']);
    } else {
      this.router.navigate(['/']);
    }
  }
}
