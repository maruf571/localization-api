import { NgModule, ContentChildren } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthLayoutComponent } from '../layout/auth-layout/auth-layout.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { ConfirmSignupComponent } from './confirm-signup/confirm.signup.component';
import { ForgotPasswordComponent } from './forgot-password/forgot.password.component';
import { SignoutComponent } from './signout/signin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';

const routes: Routes = [
  {
    path: '', component: AuthLayoutComponent,
    children:[
      { path: '', redirectTo: 'signin', pathMatch: 'full' },
      { path:'signin', component: SigninComponent },
      { path:'signout', component: SignoutComponent },
      { path:'signup', component: SignupComponent },
      { path:'confirm-signup', component: ConfirmSignupComponent },
      { path:'forgot-password', component: ForgotPasswordComponent },
      { path:'forbidden', component: ForbiddenComponent },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRouteModule { }