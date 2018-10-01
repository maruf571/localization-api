import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostSingleComponent } from './components/post-single/post-single.component';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';

const routes: Routes = [
  { 
    path: '', 
    component: AdminLayoutComponent,
    children:[
      { path: '', redirectTo: 'post-list', pathMatch: 'full' },
      { path: 'post-list', component: PostListComponent },
      { path: 'post-single', component: PostSingleComponent },
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostRouteModule { }