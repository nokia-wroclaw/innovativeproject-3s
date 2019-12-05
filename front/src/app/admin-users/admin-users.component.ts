import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Select, Store } from '@ngxs/store';
import {UserState} from '../states/user.state';
import {User} from '../models/user';
import {Observable} from 'rxjs';
import {AddUser, DeleteUser, UpdateUser, GetUsers} from '../actions/user.action';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  @Select(UserState.getUserList) userList: Observable<User[]>;

  userForm: FormGroup;
  loading = false;

  constructor(private fb: FormBuilder, private store: Store) { }

  ngOnInit() {
    this.store.dispatch(new GetUsers());
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      type: ['', Validators.required]
    });
  }

  get f() { return this.userForm.controls; }

  onSubmit() {
    if (this.userForm.valid) {
      this.loading = true;
      const uname: string = this.f.username.value;
      const pass: string = this.f.password.value;
      const t: string = this.f.type.value;

      this.store.dispatch(new AddUser({id: null, token: null, email: null, username: uname, password: pass, created: null, type: t}))
      .subscribe(() => this.userForm.reset());

      this.loading = false;
    }
  }

}
