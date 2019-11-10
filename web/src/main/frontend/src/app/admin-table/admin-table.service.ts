import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserComplaintsDto} from "../models/userComplaintsDto.model";

@Injectable({
  providedIn: 'root'
})

export class AdminTableService {
  private usersURL = '/api/v1/user/users/all';
  // private usersURL = 'http://localhost:9990/api/v1/user/users/all';
  private deactivateURL = '/api/v1/user/users/';
  private activateURL = '/api/v1/users/';
  constructor(private http: HttpClient) {
  }

  deactivateUser(id: number) {
    // @ts-ignore
    return this.http.post(this.deactivateURL + id + '/deactivate');
  }

  activateUser(id: number) {
    // @ts-ignore
    return this.http.post(this.activateURL + id + '/activate');
  }

  getAllSpeakers() {
    return this.http.get(this.usersURL);
  }
  getUsers(usersPerPage : number, currentPage: number) {
    const queryParams = `?pagesize=${usersPerPage}&page=${currentPage}`;
    return this.http.get<{users: UserComplaintsDto[], usersCount : number}>(
        this.usersURL+queryParams
      );
  }

}
