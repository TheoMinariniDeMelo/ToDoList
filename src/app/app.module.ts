import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PasswordModule } from 'primeng/password';
import { CheckboxModule } from 'primeng/checkbox';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ConfirmationService } from 'primeng/api';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    InputTextModule,
    FormsModule,
    BrowserAnimationsModule,
    PasswordModule,
    CheckboxModule,
    RadioButtonModule,
    AuthModule
  ],
  providers: [
    FormsModule,
    InputTextModule,
    BrowserAnimationsModule,
    PasswordModule,
    CheckboxModule,
    RadioButtonModule,
    ConfirmationService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {  }
