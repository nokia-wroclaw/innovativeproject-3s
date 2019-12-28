import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Scan } from '../models/scan';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ScanService {
  scanUrl = environment.url + '/scans';

  constructor(private http: HttpClient) { }

  fetchScans(email: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Email: email
      })
    };
    return this.http.get<Scan[]>(this.scanUrl, httpOptions).pipe(map(scans => scans as Scan[]));
  }

  addScan(payload: Scan) {
    return this.http.post<Scan>(this.scanUrl, payload);
  }

  deleteScan(id: number) {
    return this.http.delete(this.scanUrl + '/' + id);
  }

  triggerScan() {
      return this.http.get<any>('http://localhost:8080/trivy');
  }
}
