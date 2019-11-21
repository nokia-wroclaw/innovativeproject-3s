import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  projects = [{
    name: 'Project 1',
    tools: [{id: '123', tool: 'tool 1'},
    {id: '213', tool: 'tool 2'},
    {id: '321', tool: 'tool 3'}],
    date: '01-01-2019',
    log: 'test log',
    status: 'negative'
    },
    {
      name: 'Project 2',
      tools: [{id: '123', tool: 'tool 1'},
      {id: '213', tool: 'tool 2'},
      {id: '321', tool: 'tool 3'}],
      date: '01-01-2019',
      log: 'test log',
      status: 'waiting'
      }
  ];

  constructor() { }

  ngOnInit() {
    for (let i = 3; i < 10; i++) {
      this.projects.push({name: 'Project ' + i,
        tools: [{id: '123', tool: 'tool 1'},
        {id: '213', tool: 'tool 2'},
        {id: '321', tool: 'tool 3'}],
        date: '01-01-2019',
        log: 'test log',
        status: 'positive'
        });
    }
  }

}
