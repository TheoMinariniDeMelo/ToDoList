import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmDialogServiceRegister } from './service/confirm-dialog.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  protected mommentForm!: FormGroup;


  ngOnInit(): void {
    this.mommentForm = new FormGroup({
      user: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required, Validators.email, Validators.maxLength(64)]),
      password: new FormControl("", [Validators.required])
    })
  }

  get email() {
    return this.mommentForm.get("email");
  }
  get user() {
    return this.mommentForm.get("user");
  }
  get password() {
    return this.mommentForm.get("password")
  }

  constructor(protected confirmationServiceRegister: ConfirmDialogServiceRegister){}
  onSubmit():void{
      this.mommentForm
    }
  confirm(){
    this.confirmationServiceRegister.confirmation(this.accept, this.reject)
  }
  accept(){
    this.onSubmit()
  }

  reject = () => {
    return null
  }
}