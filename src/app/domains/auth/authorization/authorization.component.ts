<<<<<<< HEAD:src/app/auth/authorization/authorization.component.ts
import { ConfirmDialogServiceRegister } from './../register/service/confirm-dialog.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './services/login.service';
=======
import { ConfirmDialogServiceRegister } from '../register/service/confirm-dialog.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './service/login.service';
>>>>>>> a176854808433993aeb09551c51b2f9b85aaf62f:src/app/domains/auth/authorization/authorization.component.ts

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.scss']
})
export class AuthorizationComponent implements OnInit {
  protected mommentForm!: FormGroup;

  ngOnInit():void{
    this.mommentForm = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [Validators.required])
    })
  }

  constructor(protected confirmationServiceRegister: ConfirmDialogServiceRegister, protected login:LoginService){}
  onSubmit():void{
    console.table(this.mommentForm.value)
      this.login.onSubmit(this.mommentForm);
    }
}