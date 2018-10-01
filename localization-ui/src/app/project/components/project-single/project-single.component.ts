import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../project.service';
import { OnInit, Component } from '@angular/core';

@Component({
  selector: 'project-single',
  templateUrl: './project-single.component.html',
  styleUrls: ['./project-single.component.css']
})
export class ProjectSingleComponent implements OnInit {

  private project:any = {};
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private service: ProjectService
  ) { }


  ngOnInit() {
    
    const projectId = this.activeRoute.snapshot.queryParamMap.get('projectId');
    if (projectId != null) {
      console.log(projectId);
      this.service.findOne(projectId).subscribe(resp => {
        this.project = resp;
      });
    }
  }
  
  submit(project) {
    console.log(project);
    this.service.submit(project).subscribe(resp => {
      this.router.navigate(['/project/project-list']);
    })
  }   

  compareFn(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }

}