import { HttpClient, HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { TokenService } from '../../providers/servicesForAuth/token.service';

@Injectable({
  providedIn: 'root'
})
export class HomeResolver implements Resolve<any> {
  constructor(private httpClient: HttpClient) {}

  resolve(
    route: ActivatedRouteSnapshot,
  ): Observable<any> | boolean {
    const title = route.paramMap.get('title') || '';
    const page = route.paramMap.get('page') || '';
    const pageSize = route.paramMap.get('pageSize') || '';
    const stateParam = route.paramMap.get('state') || '';
    const apiUrl = `http://localhost:8000/tasks/source?title=${title}&page=${page}&pageSize=${pageSize}&state=${stateParam}`;
    
    // Retorna um Observable que aguarda a conclusão da solicitação HTTP antes de retornar true.
    return this.httpClient.get(apiUrl, {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': `Bearer ${TokenService.getToken()?.replaceAll("\"","")}`
      }
    }).pipe(
      switchMap(data => {
        // A solicitação HTTP foi concluída. Agora você pode atribuir os dados ao route.data se necessário.
        route.data = data;
        // Retorna true para indicar que a resolução foi concluída com sucesso.
        console.log(data)
        return [true];
      })
    );
  }
}
