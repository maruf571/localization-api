
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthService } from '../auth.service';


@Component({
    templateUrl: 'signin.component.html',
    styleUrls: ['signin.component.css']
})
export class SigninComponent implements OnInit {
    user = {};
    loading = false;
    submitted = false;
    returnUrl: string;
    error = '';

    constructor(
        private formBuilder: FormBuilder,
        private activeRoute: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthService) {}

    ngOnInit() {
  
        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.activeRoute.snapshot.queryParams['returnUrl'] || '/dashboard';
    }


    onSubmit(user) {
        this.submitted = true;

        this.loading = true;
        this.authenticationService.login(user.email, user.password)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);
                },
                error => {
                    this.error = error;
                    this.loading = false;
                });
    }
}
