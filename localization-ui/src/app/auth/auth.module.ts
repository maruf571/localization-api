import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRouteModule } from './auth.routes';
import { LayoutModule } from '../layout/layout.module';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { ConfirmSignupComponent } from './confirm-signup/confirm.signup.component';
import { ForgotPasswordComponent } from './forgot-password/forgot.password.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from './auth.service';
import { SignoutComponent } from './signout/signin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthRouteModule,
    LayoutModule,
  ],
  declarations: [
    SigninComponent,
    SignoutComponent,
    SignupComponent,
    ConfirmSignupComponent,
    ForgotPasswordComponent,
    ForbiddenComponent,
  ],
  providers: [
    AuthService,
  ]
})
export class AuthModule { }