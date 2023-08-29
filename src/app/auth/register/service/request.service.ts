import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import {  ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(protected httpClient: HttpClient, protected route: Router){
  }
  onSubmit(mommentForm: FormGroup): void{
   if(mommentForm.valid){
     this.httpClient.post("http://localhost:8080/account/register", mommentForm.value).subscribe((response)=>{
      response == null? this.route.navigate(['/login']) : null;
     })
  }
  }
}
