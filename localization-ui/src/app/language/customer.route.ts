import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerSingleComponent } from './components/customer-single/customer-single.component';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AuthGuard } from '../auth/auth-guard';
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
      { path: 'customer-list', component: CustomerListComponent },
      { path: 'customer-single', component: CustomerSingleComponent }
    ],
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRouteModule { }