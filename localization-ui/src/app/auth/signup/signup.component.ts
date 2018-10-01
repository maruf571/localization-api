import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthService } from '../auth.service';


@Component({
    templateUrl: 'signup.component.html'
})
export class SignupComponent {
   
    private user = {};
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authService: AuthService) {}

        
  submit(user){
    this.authService.signup(user).subscribe(resp => {
      this.router.navigate(['/auth/signin']);
    })
  }
}
