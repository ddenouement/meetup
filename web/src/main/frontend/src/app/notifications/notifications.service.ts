import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class NotificationsService {
  private allURL = 'api/v1/user/notifications';
  private countURL = 'api/v1/user/notifications/count';
  private readURL = 'api/v1/user/notifications/';

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get(this.allURL);
  }

  countAll() {
    return this.http.get(this.countURL);
  }

  read(id: number) {
    return this.http.delete(this.readURL + id);
  }
}
