import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as moment from "moment";
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators';
import * as jwt_decode from "jwt-decode";
@Injectable()
export class AuthService {


    private signupApi = environment.REST_API_URL + 'auth/sign-up';
    private signinApi = environment.REST_API_URL + 'auth/login';
    constructor(private http: HttpClient) { }

    login(email:string, password:string ) {
        return this.http.post<any>(this.signinApi, {email, password})
        .pipe(
            map(resp => this.setSession(resp))
        ); 
        
    }

    signup(user): Observable<any>{
        return  this.http.post(this.signupApi, user) 
    }
    
    private setSession(authResult) {

        let tokenInfo = this.getDecodedAccessToken(authResult.token);
        localStorage.setItem('userRoles', JSON.stringify(tokenInfo.roles));
        localStorage.setItem('token', authResult.token);
        localStorage.setItem('refreshToken', authResult.refreshToken);
    }   

    logout() {
        localStorage.removeItem("userRoles");
        localStorage.removeItem("token");
        localStorage.removeItem("refreshToken");
    }

    public isLoggedIn() {
        let token = localStorage.getItem('token');
        if(token == null)
            return false;

        return true;
        //return moment().isBefore(this.getExpiration(token));
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    getExpiration(token: any) {
        let tokenInfo = this.getDecodedAccessToken(token);
        let expireDate = tokenInfo.exp;
        return moment(expireDate);
    }  

    roleMatch(requiredRoles, userRoles): boolean {
        var isMatch = false;
        requiredRoles.forEach(element => {
          if (userRoles.indexOf(element) > -1) {
            isMatch = true;
            return false;
          }
        });
        return isMatch;
      }
    getDecodedAccessToken(token: string): any {
        try{
            return jwt_decode(token);
        }
        catch(Error){
            return null;
        }
      }
}