import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: 'app-root.html'
})
export class AppComponent {
  public text: string;
  constructor(
    private httpClient: HttpClient
  ) {}

  public foo(): void {
    this.httpClient
      .get("http://localhost:9990/hello")
      .subscribe(_ => this.text += _["a"]);
  }
}
