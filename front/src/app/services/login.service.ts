import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUrl = environment.url + '/validateLogin';

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<any>(this.loginUrl, {email, password}).pipe(map(user => user as User));
  }

  logout() {
    return of(null);
  }
}
