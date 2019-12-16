import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  selected = {
    id: 1,
    name: 'Project 1',
    tools: [{id: 1, tool: 'tool 1', date: '01/01/2019 14:00', status: 'positive'},
    {id: 2, tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {id: 3, tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {id: 4, tool: 'tool 3', date: '01/01/2019 14:00', status: 'negative'},
    {id: 5, tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {id: 6, tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'},
    {id: 7, tool: 'tool 2', date: '01/01/2019 14:00', status: 'positive'}],
    log: 'test log',
    status: 'negative'
  };

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(p => {
      console.log(p.id);
    });
  }

  trigger(id: any) {
    // TODO manualne odpalanie skanu
    console.log(id);
  }

}
