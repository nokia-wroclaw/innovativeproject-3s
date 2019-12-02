import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
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
