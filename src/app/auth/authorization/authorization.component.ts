import { ConfirmDialogServiceRegister } from './../register/service/confirm-dialog.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './service/login.service';

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
