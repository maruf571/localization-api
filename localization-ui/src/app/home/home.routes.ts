import { NgModule, ContentChildren } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PublicLayoutComponent } from '../layout/public-layout/public-layout.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {
    path: '', component: PublicLayoutComponent,
    children:[
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      {path:'home', component: HomeComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRouteModule { }