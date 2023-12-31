import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenService } from '../../../providers/servicesForAuth/token.service';
interface LoginResponse {
  token: string;
}
@Injectable({
  providedIn: 'root'
})

export class LoginService {
  constructor(
    protected tokenService: TokenService,
    protected httpClient: HttpClient,
    protected route: Router
  ) {}

  onSubmit(loginForm: FormGroup): void {
    this.httpClient.post<LoginResponse>("http://localhost:8000/account/login", loginForm.value)
      .subscribe(
        (response: LoginResponse) => {
          this.tokenService.hashStorageGenerator(response.token.replace(/^"|"$|(")+/g, ''));
          console.log(response.token.replace(/^"|"$|(")+/g, ''))
          this.route.navigate(["/home"])
        }
      );
  }
}
