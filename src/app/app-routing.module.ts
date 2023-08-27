import {  NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorizationComponent } from './auth/authorization/authorization.component';
import { RegisterComponent } from './auth/register/register.component';

const routes: Routes = [
  {
    path: "login",
    component: AuthorizationComponent
  }
  ,
  {
    path: "register",
    component: RegisterComponent
    
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
