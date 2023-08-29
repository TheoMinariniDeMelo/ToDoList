import { TokenService } from './token.service';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(protected tokenService:TokenService,protected httpClient: HttpClient, protected route: Router){
  }
  onSubmit(mommentForm: FormGroup): void{
    this.httpClient.post("http://localhost:8080/account/login", mommentForm.value)
    .subscribe(
      (response: any) => {
        this.tokenService.hashStorageGenerator(response.token);
      }
    );
  }
}
