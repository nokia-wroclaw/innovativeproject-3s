import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Scan } from '../models/scan';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ScanService {
  scanUrl = environment.url + '/scans';

  constructor(private http: HttpClient) { }

  fetchScans() {
    return this.http.get<Scan[]>(this.scanUrl);
  }

  addScan(payload: Scan) {
    return this.http.post<Scan>(this.scanUrl, payload);
  }

  deleteScan(id: number) {
    return this.http.delete(this.scanUrl + '/' + id);
  }
}
