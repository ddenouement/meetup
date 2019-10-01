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
      .get<Client>("/hello")
      .subscribe(z => this.text =  z.login);
  }
}

class Client {
  public login: string;
  public password: string;
  public fullCreds() : string {
    return `${this.login} - ${this.password}`;
  }
}
