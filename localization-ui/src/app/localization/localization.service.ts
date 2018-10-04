import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest, HttpHeaders } from "@angular/common/http";
import { environment } from '../../environments/environment';
import { Observable } from "rxjs/Observable";
import { RequestOptions } from '@angular/http';


@Injectable()
export class LocalizationService {

  private api = environment.REST_API_URL + 'protected/localizations/';
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

  export(languageId): Observable<any>{
    return this.http.get(this.api + "language/"+languageId+"/export", {responseType: 'blob'})
  }

  uploadFile(languageId, file: File): Observable<any>{
    
    let formData = new FormData();
    formData.append('file', file);
    let headers = new HttpHeaders();
    headers.set('Content-Type', '');
    headers.set('Accept', "multipart/form-data");

    return this.http.post(this.api + "language/"+languageId+"/import", formData, {
      headers
    });
  }
    
}