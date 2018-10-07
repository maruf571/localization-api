import { Component, OnInit } from '@angular/core';
import { PostService } from '../../post.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'post-single',
  templateUrl: './post-single.component.html',
  styleUrls: ['./post-single.component.css']
})
export class PostSingleComponent implements OnInit {

  post:any = {};
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private postService: PostService
  ) { }

  ngOnInit() {
    const postId = this.activeRoute.snapshot.queryParamMap.get('postId');
    if(postId != null){
      this.postService.findOne(postId)
      .subscribe(resp =>{
        this.post = resp;
      });
    }
  }


  submit(post){
    this.postService.submit(post).subscribe(resp => {
      this.router.navigate(['/post/post-list']);
    })
  }

}
