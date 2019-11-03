import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
// @ts-ignore
import { Response} from "@angular/common/http";
import {Comment} from '../models/comment';
import {Observable} from "rxjs";
import {CommentDto} from '../models/commentDto'
import {map} from "rxjs/operators";


@Injectable()
export class Commentsectionservice {
  constructor(private http: HttpClient) {
  }

  //TODO
  private commentsURL = '/api/v1/comments';

  getComments(): Observable<CommentDto[]> {
    return this.http.get<CommentDto[]>(this.commentsURL).pipe(map((response: Response) => response));
  }

  createComment(l: Comment) {
    return this.http.post(this.commentsURL, l);
  }

 //todo
  /*deleteComment(id: number) {
    return this.http.delete(this.languagesURL + '/' + id);
  }*/
}
