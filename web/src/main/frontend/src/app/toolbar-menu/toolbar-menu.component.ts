import {Component, OnInit} from '@angular/core';
import {LoginService} from "../login/login.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-toolbar-menu',
  templateUrl: './toolbar-menu.component.html',
  styleUrls: ['./toolbar-menu.component.scss']
})

export class ToolbarMenuComponent implements OnInit {
  private _authService: LoginService;

  constructor(authService: LoginService) {
    this._authService = authService;
  }

  ngOnInit() {
  }
}
