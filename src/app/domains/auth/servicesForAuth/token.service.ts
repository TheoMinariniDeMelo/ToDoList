import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  hashStorageGenerator(token:string){
    if(token){
      localStorage.setItem('token', JSON.stringify(token))
    }
    console.log (localStorage.getItem('token'))
  }
  static getToken(){
    return localStorage.getItem('token')
  }
}
