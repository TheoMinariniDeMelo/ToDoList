import { Component, OnInit } from '@angular/core';
import { ConfirmDialogServiceRegister } from './confirm-dialog.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  protected mommentForm!: FormGroup;

  ngOnInit():void{
    this.mommentForm = new FormGroup({
      user: new FormControl("user", [Validators.required]),
      eMail: new FormControl("email",[Validators.required, Validators.email,Validators.maxLength(64)]),
      password: new FormControl("password", [Validators.required])
    })
  }

  constructor(protected confirmationServiceRegister: ConfirmDialogServiceRegister){};
  onSubmit():void{
      this.mommentForm
    }
  confirm(){
    this.confirmationServiceRegister.confirmation(this.accept, this.reject)
  }
  accept(){
    this.onSubmit()
  }
  reject(){
    console.log("no")
  }
}
