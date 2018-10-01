import {Injectable} from '@angular/core';
import {Headers, Http, RequestOptions, Response} from '@angular/http';


@Injectable()
export class HomeService {

  constructor(private _http: Http) {
  }

}