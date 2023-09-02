import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { task } from './task-interface';
import { map } from 'rxjs/operators'; // Importe o operador map do 'rxjs/operators'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataTasksService {
  constructor() {""}

  // Modifique o método para receber um array de tasks em vez de um ActivatedRoute
  build(route: ActivatedRoute): Observable<task[]> {
    // Use o operador map para mapear os dados para um array de tarefas
    return route.data.pipe(
      map((data: any) => 
      data.content
      )
    );
}
}