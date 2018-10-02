import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';
import { ProjectRouteModule } from './project.route';
import { ProjectService } from './project.service';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { ProjectSingleComponent } from './components/project-single/project-single.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ProjectRouteModule,
    LayoutModule,
  ],
  declarations: [
    ProjectSingleComponent,
    ProjectListComponent,
  ],
  exports: [
  ],
  providers:[
    ProjectService, 
  ]
})
export class ProjectModule { }