import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Project } from '../models/project';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  projectUrl = environment.url + '/projects';

  constructor(private http: HttpClient) { }

  fetchProjects() {
    return this.http.get<Project[]>(this.projectUrl);
  }

  addProject(payload: Project) {
    return this.http.post<Project>(this.projectUrl, payload);
  }

  deleteProject(id: number) {
    return this.http.delete(this.projectUrl + '/' + id);
  }

  updateProject(payload: Project, id: number) {
    return this.http.put<Project>(this.projectUrl + '/' + id, payload);
  }
}
