import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  images = [1, 2, 3].map(() => `https://picsum.photos/900/500?random&t=${Math.random()}`);
  private userURL = '/api/v1/user/profile';
  private subscriptions: Subscription = new Subscription();
  constructor(private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    this.subscriptions.add(this.http.get(this.userURL).subscribe(res=>{
      if (res['userDTO'].roles.includes("SPEAKER")) {
        this.router.navigate(['/speaker-profile']);
      } else if (res['userDTO'].roles.includes("ADMIN") ) {
        this.router.navigate(['/admin-table']);
      } else {
        this.router.navigate(['/listener-profile']);
      }
    }));
  }
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

}
