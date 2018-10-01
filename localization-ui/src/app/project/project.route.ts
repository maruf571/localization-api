import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AuthGuard } from '../auth/auth-guard';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { ProjectSingleComponent } from './components/project-single/project-single.component';

const routes: Routes = [
  { 
    path: '',  component:AdminLayoutComponent,
    canActivate:[AuthGuard],
    data:{
      requiredRoles:["ROLE_ADMIN"]
    },
    children: [
      { path: '', redirectTo: 'project-list', pathMatch: 'full' },
      { path: 'project-list', component: ProjectListComponent },
      { path: 'project-single', component: ProjectSingleComponent }
    ]  
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectRouteModule { }