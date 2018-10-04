import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../project.service';

@Component({
  selector: 'project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {

  projects = [];
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


  navigateToSingle(projectId){    
    this.router.navigate(
      ['/project/project-single'], {
        queryParams:{ 
          projectId: projectId,
        }
      }
  );
}

  navigateToLanguage(projectId){
    this.router.navigate(
      ['/language/language-list'], {
        queryParams:{ 
          projectId: projectId,
        }
      }
    );
  }

}