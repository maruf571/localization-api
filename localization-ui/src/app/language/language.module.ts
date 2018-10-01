import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';
import { AuthGuard } from '../auth/auth-guard';
import { LanguageRouteModule } from './language.route';
import { LanguageListComponent } from './components/language-list/language-list.component';
import { LanguageSingleComponent } from './components/language-single/language-single.component';
import { LanguageService } from './language.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    LanguageRouteModule,
    LayoutModule,
  ],
  declarations: [
    LanguageListComponent,
    LanguageSingleComponent
  ],
  exports: [
  ],
  providers:[
    LanguageService,
    AuthGuard
  ]
})
export class LanguageModule { }