import { Component, OnInit } from '@angular/core';
import { DataTasksService } from './../services/data-tasks.service';
import { ActivatedRoute} from '@angular/router';
import { task } from './../services/task-interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  protected taskList: task[] = []; // Propriedade para armazenar a lista de tarefas

  constructor(
    private readonly dataTasksService: DataTasksService,
    private readonly route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Atribua a lista de tarefas transformada à propriedade taskList
     this.dataTasksService.build(this.route).subscribe(value=>{
      this.taskList = value
    });  
    console.log(this.taskList)
  }
}
