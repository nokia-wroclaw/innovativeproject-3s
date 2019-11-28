import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-tools',
  templateUrl: './tools.component.html',
  styleUrls: ['./tools.component.css']
})
export class ToolsComponent implements OnInit {

  toolList = [
    {
      id: '1',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '2',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '3',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    },
    {
      id: '123',
      tool: 'test 1',
      date: '01-01-2019',
      status: 'positive',
      log: 'logs'
    }
  ];

  constructor(private ds: DataService) { }

  ngOnInit() {
  }
}
