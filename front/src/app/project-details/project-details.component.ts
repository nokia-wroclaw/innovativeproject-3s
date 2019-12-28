import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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

  constructor(private route: ActivatedRoute, private scanService: ScanService) { }

  ngOnInit() {
  }

  trigger(id: any) {
    // TODO manualne odpalanie skanu
    this.loading = true;
    this.scanService.triggerScan().subscribe(result => {
      this.loading = false;
      console.log(result);
    }, error => {
      this.loading = false;
      console.log(error);
    });
  }

}
