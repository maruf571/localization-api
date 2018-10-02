import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';
import { Observable } from "rxjs/Observable";

@Injectable()
export class LanguageService {

  private api = environment.REST_API_URL + 'protected/languages/';
  constructor(private http: HttpClient) {
  }

  findAll(query): Observable<any> {
    query = query == null ? "" : query;
    return this.http.get(this.api + query)
  }

  findOne(id): Observable<any> {
    return this.http.get(this.api + id)
  }

  submit(entity): Observable<any> {
    if (entity.id == null) {
      return this.http.post(this.api, entity)
    } else {
      return this.http.put(this.api, entity)
    }

  }

}