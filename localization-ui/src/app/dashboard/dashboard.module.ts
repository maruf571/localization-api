import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardRouteModule } from './dashboard.routes';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { Block1Component } from './components/partial/block1/block1.component';
import { LayoutModule } from '../layout/layout.module';
import { Block2Component } from './components/partial/block2/block2.component';

@NgModule({
  imports: [
    CommonModule,
    DashboardRouteModule,
    LayoutModule
  ],
  declarations: [
    DashboardComponent,
    Block1Component,
    Block2Component
  ],
  exports: [
  ]
})
export class DashboardModule { }