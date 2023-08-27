import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmDialogServiceRegister } from '../register/confirm-dialog.service';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.scss']
})
export class AuthorizationComponent implements OnInit {
  protected mommentForm!: FormGroup;

  ngOnInit():void{
    this.mommentForm = new FormGroup({
      user: new FormControl("user", [Validators.required]),
      password: new FormControl("password", [Validators.required])
    })
  }

  constructor(protected confirmationServiceRegister: ConfirmDialogServiceRegister){};
  onSubmit():void{
      this.mommentForm
    }
}
