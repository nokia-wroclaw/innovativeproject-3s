import { Component, OnInit } from '@angular/core';
import { CdkTreeNodeToggle } from '@angular/cdk/tree';
import { Store, Select } from '@ngxs/store';
import { LoginState } from '../states/login.state';
import { ProjectState } from '../states/project.state';
import { Observable } from 'rxjs';
import { Project } from '../models/project';
import { GetProjects, AddProject } from '../actions/project.action';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../models/user';
import { Scan } from '../models/scan';
import { $ } from 'protractor';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  @Select(ProjectState.getProjectList) projects: Observable<Project[]>;

  projectForm: FormGroup;
  userForm: FormGroup;
  scanForm: FormGroup;
  currentUser: any;

  isOpen = false;

  userList = [];
  scanList = [];

  columns = 3;
  rows = '2:1.5';
  blurValue = 'blur(0px)';

  constructor(private store: Store, private fb: FormBuilder) {
    this.currentUser = this.store.selectSnapshot(LoginState.userDetails);
    this.projectForm = this.fb.group({
      name: ['', Validators.required]
    });

    this.userForm = this.fb.group({
      user: ['', Validators.required]
    });

    this.scanForm = this.fb.group({
      tool: ['', Validators.required],
      date: ['', Validators.required]
    });
  }

  get fp() { return this.projectForm.controls; }
  get fu() { return this.userForm.controls; }
  get fs() { return this.scanForm.controls; }

  ngOnInit() {
    this.store.dispatch(new GetProjects({email: this.currentUser.email}));
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
  }

  onResize(event) {
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
  }

  toggle() {
    this.blurValue === 'blur(0px)' ? this.blurValue = 'blur(10px)' : this.blurValue = 'blur(0px)';
    this.userList = [];
    this.scanList = [];
  }

  addUser() {
    this.userList.push({email: this.fu.user.value, permission: ['USER']});
  }

  addScan() {
    this.scanList.push({toolName: this.fs.tool.value, date: String(this.fs.date.value.toLocaleString())});
  }

  onSubmit() {
    console.log({name: this.fp.name.value, users: this.userList as User[], scans: this.scanList as Scan[]} as Project);
    this.store.dispatch(new AddProject({
      name: this.fp.name.value,
      users: this.userList as User[],
      scans: this.scanList as Scan[]
    } as Project,
    this.currentUser.email)).subscribe(result => {
      console.log('Project added');
      this.isOpen = false;
    }, error => {
      console.log('Adding project failed');
    });
  }

}
