import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerService } from './customer.service';
import { CustomerRouteModule } from './customer.route';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { CustomerSingleComponent } from './components/customer-single/customer-single.component';
import { FormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';
import { AuthGuard } from '../auth/auth-guard';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CustomerRouteModule,
    LayoutModule,
  ],
  declarations: [
    CustomerListComponent,
    CustomerSingleComponent
  ],
  exports: [
  ],
  providers:[
    CustomerService,
    AuthGuard
  ]
})
export class CustomerModule { }