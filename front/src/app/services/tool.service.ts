import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Tool } from '../models/tool';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ToolService {
  toolsUrl = environment.url + '/tools';

  constructor(private http: HttpClient) { }

  fetchTools() {
    return this.http.get<Tool[]>(this.toolsUrl);
  }

  addTool(payload: Tool) {
    return this.http.post<Tool>(this.toolsUrl, payload);
  }

  deleteTool(id: number) {
    return this.http.delete(this.toolsUrl + '/' + id);
  }
}
