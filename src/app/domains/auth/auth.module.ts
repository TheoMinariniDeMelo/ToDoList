import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { AuthorizationComponent } from './authorization/authorization.component';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PasswordModule } from 'primeng/password';
import { CheckboxModule } from 'primeng/checkbox';
import { RadioButtonModule } from 'primeng/radiobutton';
import {MatButtonModule} from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import { TooltipModule } from 'primeng/tooltip';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { RequestService } from './register/service/request.service';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { LogoComponent } from '../../logo/logo.component';
@NgModule({
  declarations: [
    AuthorizationComponent,
    RegisterComponent,
    HeaderComponent,
    LogoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    InputTextModule,
    BrowserAnimationsModule,
    PasswordModule,
    CheckboxModule,
    RadioButtonModule,
    ConfirmDialogModule,
    MatButtonModule,
    ReactiveFormsModule,
    TooltipModule,
    RouterModule
  ]
  ,
  providers:[
    RequestService
  ]
})
export class AuthModule { }