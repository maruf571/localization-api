import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable()
export class ProjectService {

  private api = environment.REST_API_URL + 'protected/projects/';
  
  constructor(private http: HttpClient) {
  }

  findAll(str?): Observable<any> {
    return this.http.get(this.api)
  }

  findOne(id): Observable<any>{
    return this.http.get(this.api + id)
  }
  
  submit(entity): Observable<any>{
    if(entity.id == null){
      return this.http.post(this.api, entity)
    }else {
      return  this.http.put(this.api, entity)
    }
    
  }

}