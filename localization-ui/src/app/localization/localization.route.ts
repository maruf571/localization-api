import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AuthGuard } from '../auth/auth-guard';
import { LocalizationListComponent } from './components/localization-list/localization-list.component';
import { LocalizationSingleComponent } from './components/localization-single/localization-single.component';
//import { AppAuthGuard } from '../../auth/app.auth-guard';

const routes: Routes = [
  { 
    path:'', 
    component: AdminLayoutComponent,
    canActivate:[AuthGuard],
    data:{
      requiredRoles:["ROLE_ADMIN"]
    },
    children:[
      { path: '', redirectTo: 'localization-list', pathMatch: 'full' },
      { path: 'localization-list', component: LocalizationListComponent },
      { path: 'localization-single', component: LocalizationSingleComponent }
    ],
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalizationRouteModule { }