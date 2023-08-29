import { ConfirmDialogServiceRegister } from './service/confirm-dialog.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  protected mommentForm!: FormGroup;

  constructor(protected httpClient: HttpClient, protected confirmationServiceRegister: ConfirmDialogServiceRegister) { }

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

  onSubmit(): void {
    console.table(this.mommentForm.value)
    if (this.mommentForm.valid) {
      const header = new HttpHeaders({
        'Content-Type': 'application/json'         
      })
      this.httpClient.post("http://localhost:8080/account/register", this.mommentForm.value,
      {headers: header}
      ).subscribe((response) => {
        console.log(response);
      });
    }
  }

  confirm() {
    this.confirmationServiceRegister.confirmation(this.accept, this.reject);
  }

  accept = () => {
    this.onSubmit();
  }

  reject = () => {
    return null
  }
}