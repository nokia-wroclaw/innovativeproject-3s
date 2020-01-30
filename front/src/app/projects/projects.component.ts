import { Component, OnInit } from '@angular/core';
import { Store, Select } from '@ngxs/store';
import { LoginState } from '../states/login.state';
import { ProjectState } from '../states/project.state';
import { Observable } from 'rxjs';
import { Project } from '../models/project';
import { GetProjects, AddProject, SetSelectedProject } from '../actions/project.action';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../models/user';
import { Scan } from '../models/scan';
import { Tool } from '../models/tool';

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
  toolForm: FormGroup;
  currentUser: any;

  userList = [];
  scanList = [];
  toolList = [];

  columns = 3;
  rows = '2:1.5';
  blurValue = 'blur(0px)';
  privateRepo = false;

  constructor(private store: Store, private fb: FormBuilder) {
    this.currentUser = this.store.selectSnapshot(LoginState.userDetails);
    this.projectForm = this.fb.group({
      name: ['', Validators.required]
    });

    this.userForm = this.fb.group({
      user: ['', Validators.required]
    });

    this.toolForm = this.fb.group({
      repoName: ['', Validators.required],
      repo: ['', Validators.required],
      private: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.scanForm = this.fb.group({
      tool: ['', Validators.required],
      date: ['', Validators.required]
    });
  }

  get fp() { return this.projectForm.controls; }
  get fu() { return this.userForm.controls; }
  get fs() { return this.scanForm.controls; }
  get ft() { return this.toolForm.controls; }

  ngOnInit() {
    this.store.dispatch(new GetProjects({email: this.currentUser.email}));
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
    this.projects.subscribe(s => console.log(s));
  }

  onResize(event) {
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
  }

  toggle() {
    this.blurValue === 'blur(0px)' ? this.blurValue = 'blur(10px)' : this.blurValue = 'blur(0px)';
    this.userList = [];
    this.scanList = [];
    this.toolList = [];
  }

  togglePrivate() {
    this.privateRepo === true ? this.privateRepo = false : this.privateRepo = true;
  }

  addUser() {
    this.userList.push({email: this.fu.user.value, permission: ['USER']});
  }

  addScan() {
    this.scanList.push({toolName: this.fs.tool.value, stringDate: String(this.fs.date.value.toLocaleString()), email: this.currentUser.email});
  }

  addTool() {
    this.toolList.push({
      name: this.ft.repoName.value,
      repo: this.ft.repo.value, isPrivate: this.privateRepo,
      login: this.ft.login.value, password: this.ft.password.value
    });
  }

  onSubmit() {
    console.log({
      name: this.fp.name.value, users: this.userList as User[], scans: this.scanList as Scan[], tools: this.toolList as Tool[]
    } as Project);
    this.store.dispatch(new AddProject({
      name: this.fp.name.value,
      users: this.userList as User[],
      scans: this.scanList as Scan[],
      tools: this.toolList as Tool[],
    } as Project, this.currentUser.email)).subscribe(result => {
      console.log('Project added');
      this.store.dispatch(new GetProjects({email: this.currentUser.email}));
      window.location.reload();
    }, error => {
      console.log('Adding project failed');
    });
  }

  set(project: Project) {
    this.store.dispatch(new SetSelectedProject(project));
  }

}
