import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  hashStorageGenerator(token:string){
    localStorage.setItem('token', JSON.stringify(token))
    console.log (localStorage.getItem('token'))
  }

}