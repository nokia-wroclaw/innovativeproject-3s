import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'http://localhost:8080/users';

  constructor(private http: HttpClient) { }

  fetchUsers() {
    return this.http.get<User[]>(`${this.url}`);
  }

  addUser(payload: User) {
    return this.http.post<User>(`${this.url}`, payload);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.url}` + id);
  }

  updateUser(payload: User, id: number) {
    return this.http.put<User>(`${this.url}` + id, payload);
  }
}
