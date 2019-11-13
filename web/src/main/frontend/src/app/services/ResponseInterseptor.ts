import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import 'rxjs/add/operator/do';
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class ResponseInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
  ){

}


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).do((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          console.log('Success');
        }
      },
      (error: any) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 404) {
            //this.router.navigate(['/not-found']);
          }
          if (error.status === 403) {
            //this.router.navigate(['/forbidden'])
          }
        }
      }
    )
  }
}
