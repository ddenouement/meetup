import {Component, OnInit} from '@angular/core';
import {LoginService} from "../services/login.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-toolbar-menu',
  templateUrl: './toolbar-menu.component.html',
  styleUrls: ['./toolbar-menu.component.scss']
})

export class ToolbarMenuComponent implements OnInit {
  private _authService: LoginService;
  login: string;

  constructor(private authService: LoginService, private httpClient: HttpClient,) {
    this._authService = authService;
  }

  ngOnInit() {
    this.httpClient.get('/api/v1/user/id').subscribe(res => {
      this.authService.logInUserBool = true;
    });

  }

  ngOnDestroy() {
  }
}
