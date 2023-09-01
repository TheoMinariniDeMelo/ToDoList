import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RequestTasksService } from './SPA/services/request-tasks.service';
import { AuthModule } from './auth/auth.module';
import { SPAModule } from './SPA/spa.module';
@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    AuthModule,
    SPAModule
    
  ],
  providers: [
    RequestTasksService,

  ]
})
export class DomainModule{ }
