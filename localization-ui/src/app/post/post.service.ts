import {Injectable} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable()
export class PostService {

  private api = environment.REST_API_URL + 'posts/';
  constructor(private http: HttpClient) {
  }

  findAll(): Observable<any> {
    return this.http.get(this.api)
  }

  findOne(id): Observable<any>{
    return this.http.get(this.api + id)
  }

  submit(post): Observable<any>{
    if(post.id){
      return this.http.put(this.api, post)
    }else {
      return  this.http.post(this.api, post)
    }
    
  }

}