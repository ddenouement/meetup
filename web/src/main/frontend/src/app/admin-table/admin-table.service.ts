import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class AdminTableService {
  private usersURL = '/api/v1/user/users';

  constructor(private http: HttpClient) {
  }

  getAllSpeakers(){
    return  this.http.get(this.usersURL);
  }

}
