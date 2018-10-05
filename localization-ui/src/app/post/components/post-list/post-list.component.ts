import { Component, OnInit } from '@angular/core';
import { PostService } from '../../post.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  posts:any = [];
  constructor(
    private postService:PostService,
    private router: Router,
    private activeRoute:ActivatedRoute
  ) { }

  ngOnInit() {
      this.postService.findAll()
      .subscribe(resp =>{
        this.posts = resp.content;
      });
  }

}