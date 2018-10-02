import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';
import { AuthGuard } from '../auth/auth-guard';
import { LocalizationListComponent } from './components/localization-list/localization-list.component';
import { LocalizationSingleComponent } from './components/localization-single/localization-single.component';
import { LocalizationService } from './localization.service';
import { LocalizationRouteModule } from './localization.route';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    LocalizationRouteModule,
    LayoutModule,
  ],
  declarations: [
    LocalizationListComponent,
    LocalizationSingleComponent
  ],
  exports: [
  ],
  providers:[
    LocalizationService,
    AuthGuard
  ]
})
export class LocalizationModule { }