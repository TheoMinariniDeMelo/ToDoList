import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  hashStorageGenerator(token:string){
    if(token){
      localStorage.setItem('token', JSON.stringify(token))
    }
  }
  static getToken(){
    return localStorage.getItem('token')
  }
}
