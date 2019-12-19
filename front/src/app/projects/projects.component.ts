import { Component, OnInit } from '@angular/core';
import { CdkTreeNodeToggle } from '@angular/cdk/tree';
import { Store, Select } from '@ngxs/store';
import { LoginState } from '../states/login.state';
import { ProjectState } from '../states/project.state';
import { Observable } from 'rxjs';
import { Project } from '../models/project';
import { GetProjects } from '../actions/project.action';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  @Select(ProjectState.getProjectList) projects: Observable<Project[]>;

  currentUser: any;

  columns = 3;
  rows = '2:1.5';

  constructor(private store: Store) {
    this.currentUser = this.store.selectSnapshot(LoginState.userDetails);
  }

  ngOnInit() {
    this.store.dispatch(new GetProjects({email: this.currentUser.email}));
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
  }

  onResize(event) {
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
  }

}
