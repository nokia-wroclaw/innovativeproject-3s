import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userUrl = environment.url + '/users';

  constructor(private http: HttpClient) { }

  fetchUsers() {
    return this.http.get<User[]>(this.userUrl);
  }

  addUser(payload: User) {
    return this.http.post<User>(this.userUrl, payload);
  }

  deleteUser(id: number) {
    return this.http.delete(this.userUrl + '/' + id);
  }

  updateUser(payload: User, id: number) {
    return this.http.put<User>(this.userUrl + '/' + id, payload);
  }
}
