import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate{
  constructor(
    protected authService: AuthService,
    protected router: Router
    ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      var userRoles = JSON.parse(localStorage.getItem('userRoles'));
      console.log(userRoles);
      if(userRoles == null || userRoles.length == 0){
        //user is not loogged in
        this.router.navigate(['/auth/signin'], { queryParams: { returnUrl: state.url }});
        return false;
      }
          
      let requiredRoles = route.data.requiredRoles;
      var match = this.authService.roleMatch(requiredRoles, userRoles);
      if(match){
          return true;
        }else{
          this.router.navigate(['/auth/forbidden']);
          return false;
      }

  }
}