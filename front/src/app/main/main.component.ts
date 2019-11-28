import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  currentUser: User;

  constructor(private ds: DataService) {
    this.currentUser = this.ds.currentUserValue;
  }

  ngOnInit() {
  }

  logout() {
    this.ds.logout();
    location.reload(true);
  }

}
