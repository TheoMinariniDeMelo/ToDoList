import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenService } from '../auth/servicesForAuth/token.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    const token = TokenService.getToken();
    const isHomeRoute = route.paramMap.has("home");

    if (isHomeRoute && token) {
      return true;
    } else if (isHomeRoute && !token) {
      return this.router.createUrlTree(['/login']);
    } else if (!isHomeRoute && token) {
      return true;
    } else {
      return this.router.createUrlTree(['/login']);
    }
  }
}
