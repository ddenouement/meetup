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

  private articlesURL = 'api/v1/user/articles/';
  private usersURL = 'api/v1/users/';
  private userURL = 'api/v1/user/';

  getComments(idArticle:number): Observable<CommentDto[]> {
    return this.http.get<CommentDto[]>(this.articlesURL+idArticle+"/comments").pipe(
      map((response: Response) => response) );
  }

  createComment( idArticle:number, l: Comment) {
    return this.http.post<CommentDto>(this.articlesURL+idArticle+"/comments", l).pipe(
      map((response: Response) => response) );
  }

  getUserRole(id: number){
    return this.http.get(this.usersURL+id+"/role",  {responseType: 'text'});
  }
  getUserLogin( ){
    return this.http.get(this.usersURL+"current"+"/login",  {responseType: 'text'});
  }
 deleteComment(idComment: number) {
    return this.http.delete(this.articlesURL + 'comments/' + idComment);
  }

  getUserId() {
    return this.http.get(this.userURL+'id', {responseType:'text'});
  }
}
