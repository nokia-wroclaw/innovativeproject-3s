import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../services/data.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  userList= [
    {id: 1, username: 'user 1', created: '01-01-2019', type: 'admin'}, {id: 2, username: 'user 2', created: '01-01-2019', type: 'admin'},
    {id: 3, username: 'user 3', created: '01-01-2019', type: 'user'}, {id: 4, username: 'user 4', created: '01-01-2019', type: 'user'},
    {id: 5, username: 'user 5', created: '01-01-2019', type: 'user'}, {id: 6, username: 'user 6', created: '01-01-2019', type: 'user'},
    {id: 7, username: 'user 7', created: '01-01-2019', type: 'user'}, {id: 8, username: 'user 8', created: '01-01-2019', type: 'user'},
    {id: 9, username: 'user 9', created: '01-01-2019', type: 'user'}, {id: 10, username: 'user 10', created: '01-01-2019', type: 'user'},
    {id: 11, username: 'user 11', created: '01-01-2019', type: 'user'}, {id: 12, username: 'user 12', created: '01-01-2019', type: 'user'},
    {id: 13, username: 'user 13', created: '01-01-2019', type: 'user'}, {id: 14, username: 'user 14', created: '01-01-2019', type: 'user'},
    {id: 15, username: 'user 15', created: '01-01-2019', type: 'user'}, {id: 16, username: 'user 16', created: '01-01-2019', type: 'user'}
  ];

  newUserForm: FormGroup;
  submitted = false;
  scanLog: string;

  constructor(private fb: FormBuilder, private db: DataService) { }

  ngOnInit() {
    this.newUserForm = this.fb.group({
      newusername: ['', Validators.required],
      newpassword: ['', Validators.required],
      type: ['', Validators.required]
    });
  }

  get f() { return this.newUserForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.newUserForm.invalid) {
      return;
    }

    const uname = this.f.newusername.value;
    const pass = this.f.newpassword.value;
    const t = this.f.type.value;

    console.log(uname, pass, t);
  }

  send() {
    const s = (document.getElementById('scanline') as HTMLInputElement).value;
    this.db.sendScan(s).pipe(first()).subscribe(data => {
      this.scanLog = data;
    });
  }

}
