import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './SPA/home/home.component';
import { NotFoundComponent } from './SPA/not-found/not-found.component'; 
import { HttpClientModule } from '@angular/common/http';
import { RequestTasksService } from './SPA/services/request-tasks.service';

@NgModule({
  declarations: [
    HomeComponent,
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    
  ],
  providers: [
    RequestTasksService
  ]
})
export class DomainModule{ }
