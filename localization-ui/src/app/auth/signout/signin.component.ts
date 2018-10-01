
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
    template:``
})
export class SignoutComponent implements OnInit {
    constructor(
        private router: Router,
        private authService: AuthService) {
        }

    ngOnInit() {
  
        // reset login status
        this.authService.logout();

        //go to home
        this.router.navigate(['/home']);
    }
}
