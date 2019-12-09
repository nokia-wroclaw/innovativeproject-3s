import { Component, OnInit } from '@angular/core';
import { ScanState } from '../states/scan.state';
import {Select, Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import { Scan } from '../models/scan';
import { GetScans, DeleteScan } from '../actions/scan.action';

@Component({
  selector: 'app-tools',
  templateUrl: './tools.component.html',
  styleUrls: ['./tools.component.css']
})
export class ToolsComponent implements OnInit {

  @Select(ScanState.getScanList) scanList: Observable<Scan[]>;

  constructor(private store: Store) { }

  ngOnInit() {
    this.store.dispatch(new GetScans());
  }
}
