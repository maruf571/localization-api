import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../project.service';

@Component({
  selector: 'project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {

  private projects = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute, 
    private service: ProjectService
  ) {}

  ngOnInit() {
    this.service.findAll("").subscribe(resp => { 
      this.projects = resp.content;
    });
    
  }

}