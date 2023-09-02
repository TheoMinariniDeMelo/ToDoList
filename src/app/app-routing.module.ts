import { GuardService } from './guard/guard.service';
import {  NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorizationComponent } from './domains/auth/authorization/authorization.component';
import { RegisterComponent } from './domains/auth/register/register.component';
import { HomeComponent } from './domains/SPA/home/home.component';
import { NotFoundComponent } from './domains/SPA/not-found/not-found.component'; 
import { HomeResolver } from './domains/SPA/home/home.resolver';

const appRoutes: Routes = [ 
      {
        path: "", 
        redirectTo: "/home",
        pathMatch: "full"
      },
          
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
        canActivate : [GuardService],
        component: HomeComponent,
        resolve: {tasks: HomeResolver}
      },
      {
        path: '**',
        component: NotFoundComponent
      }
      // Outras rotas filhas aqui, se necessário
    ]


@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
