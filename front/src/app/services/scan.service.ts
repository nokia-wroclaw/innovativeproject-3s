import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Scan } from '../models/Scan';

@Injectable({
  providedIn: 'root'
})
export class ScanService {
  url = 'http://localhost:8080/scans';

  constructor(private http: HttpClient) { }

  fetchScans() {
    return this.http.get<Scan[]>(`${this.url}`);
  }

  addScan(payload: Scan) {
    return this.http.post<Scan>(`${this.url}`, payload);
  }

  deleteScan(id: number) {
    return this.http.delete(`${this.url}` + id);
  }
}
