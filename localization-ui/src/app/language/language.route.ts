import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AuthGuard } from '../auth/auth-guard';
import { LanguageSingleComponent } from './components/language-single/language-single.component';
import { LanguageListComponent } from './components/language-list/language-list.component';
//import { AppAuthGuard } from '../../auth/app.auth-guard';

const routes: Routes = [
  { 
    path:'', 
    component: AdminLayoutComponent,
    canActivate:[AuthGuard],
    data:{
      requiredRoles:["ROLE_ADMIN", "ROLE_MANAGER"]
    },
    children:[
      { path: '', redirectTo: 'customer-list', pathMatch: 'full' },
      { path: 'customer-list', component: LanguageListComponent },
      { path: 'customer-single', component: LanguageSingleComponent }
    ],
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LanguageRouteModule { }