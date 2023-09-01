import { HttpClient } from '@angular/common/http';
import { Injector } from '@angular/core';
import { ResolveFn } from '@angular/router';

export const homeResolver: ResolveFn<boolean> = (route, state) => {
  const Inject = Injector.create({
      providers: [{provide: HttpClient}]
    })

    console.log("ok")

   Inject.get(HttpClient).get(`http://localhost:8000/tasks/
                  source?title=${route.paramMap.get("title") || ''}
                  &page=${route.paramMap.get("page") || ''}
                  &pageSize=${route.paramMap.get("pageSize") || ''}
                  &state=${route.paramMap.get("state") || ''}`, {headers: {'Content-Type': 'application/json'}})

    .subscribe((response: any)=>{
      if(response.status === 200){
        route.data = response
      }
    })
    console.table(route.data || "ok")
    return true
};
