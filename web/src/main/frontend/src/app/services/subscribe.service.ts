import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class SubscribeService {
  private userURL = '/api/v1/user/profile';
  constructor(private http: HttpClient) {
  }


  getUsers(){
    return this.http.get(this.userURL);
  }
}
