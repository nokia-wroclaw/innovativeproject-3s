import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { DataService } from '../services/data.service';

@Injectable()
export class ErrorInterceptor  {
    constructor(private ds: DataService) {}
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (err.status === 401) {
                this.ds.logout();
                location.reload(true);
            }

            const error = err.error.message || err.statusText;

            return throwError(error);
        }));
    }
}
