import { GuardService } from './guard/guard.service';
import {  NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorizationComponent } from './auth/authorization/authorization.component';
import { RegisterComponent } from './auth/register/register.component';
import { HomeComponent } from './main/home/home.component';
const appRoutes: Routes = [
  {
    path: '',
    canActivate: [GuardService], // Usar 'canActivate' para aplicar guarda ao roteamento
    children: [
      {
        path: 'login',
        component: AuthorizationComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'home',
        component: HomeComponent,
    }
      // Outras rotas filhas aqui, se necessário
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
