import { Component, OnInit } from '@angular/core';
import { CdkTreeNodeToggle } from '@angular/cdk/tree';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  columns = 3;
  rows = '2:1.5';

  projects = [{
    id: 1,
    name: 'Project 1',
    tools: [{tool: 'tool 1', date: '01/01/2019 14:00', status: 'positive'},
    {tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {tool: 'tool 3', date: '01/01/2019 14:00', status: 'negative'},
    {tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'}],
    log: 'test log',
    status: 'negative'
    },
    {
      id: 2,
      name: 'Project 2',
      tools: [{tool: 'tool 1', date: '01/01/2019 14:00', status: 'positive'},
      {tool: 'tool 2', date: '01/01/2019 14:00', status: 'waiting'},
      {tool: 'tool 3', date: '01/01/2019 14:00', status: 'positive'}],
      log: 'test log',
      status: 'waiting'
      }
  ];

  constructor() { }

  ngOnInit() {
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');

    for (let i = 3; i < 20; i++) {
      this.projects.push({
        id: i,
        name: 'Project ' + i,
        tools: [{tool: 'tool 1', date: '01/01/2019 14:00', status: 'positive'},
        {tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
        {tool: 'tool 3', date: '01/01/2019 14:00', status: 'positive'}],
        log: 'test log',
        status: 'positive'
        });
    }
  }

  onResize(event) {
    this.columns = (window.innerWidth <= 426) ? 1 : ((window.innerWidth <= 1025) ? 2 : 3);
    this.rows = (window.innerWidth <= 426) ? '2:1.75' : ((window.innerWidth <= 1025) ? '2:2' : '2:1.5');
  }

}
