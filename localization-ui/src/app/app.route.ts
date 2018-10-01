import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const appRoutes: Routes = [

  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'auth', loadChildren: './auth/auth.module#AuthModule' },
  { path: 'home', loadChildren: './home/home.module#HomeModule' },
  { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
  { path: 'project', loadChildren: './project/project.module#ProjectModule' },
  { path: 'language', loadChildren: './post/post.module#PostModule' },
  { path: 'localization', loadChildren: './customer/customer.module#CustomerModule' },

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule],
})

export class AppRouteModule {
}