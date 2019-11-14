import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {MeetupsService} from "../services/meetups.service";
import {ListenerProfileToUsersService} from "../services/listener-profile-to-users.service";

@Component({
  selector: 'app-listener-profile-to-users',
  templateUrl: './listener-profile-to-users.component.html',
  styleUrls: ['./listener-profile-to-users.component.scss']
})
export class ListenerProfileToUsersComponent implements OnInit {
  private badgeList: string[] = [];

  private login;
  private email;
  private listenerId: string;
  private star: number;

  constructor(public listenerService: ListenerProfileToUsersService, private httpClient: HttpClient, public route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('listenerId')) {
        this.listenerId = paramMap.get('listenerId');
        this.listenerService.getListener(+this.listenerId).subscribe(res => {
          this.badgeList = res['badges'];
          this.login = res['userDTO'].login;
          this.email = res['userDTO'].email;
          this.star =res['userDTO'].rate;
        });
      }
    });
  }

}
