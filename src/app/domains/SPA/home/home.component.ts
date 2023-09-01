import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{
  constructor(protected readonly router:Router, protected readonly activatedRouter:ActivatedRoute){
    ""
  }
  ngOnInit(){
   this.activatedRouter.data.subscribe(dados=>{
      console.log(dados)
    })
  }

}
