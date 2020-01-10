import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ScanService } from '../services/scan.service';
import { Store, Select } from '@ngxs/store';
import { ProjectState } from '../states/project.state';
import { Observable } from 'rxjs';
import { Project } from '../models/project';
import { GetProjects } from '../actions/project.action';
import { LoginState } from '../states/login.state';
import { map, concatMap, filter } from 'rxjs/operators';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  @Select(ProjectState.getSelectedProject) selected: Observable<Project>;
  currentUser: any;
  loading = false;
  success = false;
  sending = false;
  failure = false;

  constructor(private router: Router, private scanService: ScanService) {
    if (!this.selected) {
      router.navigate(['/projects']);
    }
  }

  ngOnInit() {
  }

  trigger(id: any) {
    this.sending = true;
    this.scanService.triggerScan().subscribe(result => {
      this.success = true;
      console.log(result);
    }, error => {
      this.failure = true;
      console.log(error);
    });
  }

  closeSuccess() {
    this.success = false;
  }

  closeSending() {
    this.sending = false;
  }

  closeFailure() {
    this.failure = false;
  }

}
