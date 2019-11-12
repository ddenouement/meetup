import {Component, OnInit} from '@angular/core';
import {LoginService} from "../login/login.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-toolbar-menu',
  templateUrl: './toolbar-menu.component.html',
  styleUrls: ['./toolbar-menu.component.scss']
})

export class ToolbarMenuComponent implements OnInit {
  private _authService: LoginService;
  time = new Date();
  timer;
  login: string;

  constructor(private authService: LoginService, private httpClient: HttpClient,) {
    this._authService = authService;
  }

  ngOnInit() {
    this.timer = setInterval(() => {
      this.time = new Date();
    }, 1000);
    this.httpClient.get('/api/v1/user/id').subscribe(res => {
      this.authService.logInUserBool = true;
    }, error => {
      console.warn(error);
    });

  }

  ngOnDestroy() {
    clearInterval(this.timer);
  }
}
