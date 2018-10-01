import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuillModule } from 'ngx-quill';
import { PostRouteModule } from './post.route';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostSingleComponent } from './components/post-single/post-single.component';
import { PostService } from './post.service';
import { FormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';

@NgModule({
  imports: [
    CommonModule,
    QuillModule,
    FormsModule,
    PostRouteModule,
    LayoutModule
  ],
  declarations: [
    PostListComponent,
    PostSingleComponent
  ],
  exports: [ 
  ],
  providers: [
    PostService
  ]
})
export class PostModule { }