import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-logo',
  template: `
    <img src="../../assets/image-removebg-preview.png" />
  `,        
  styles: [`
    img {
      width: 70px;
    }
  `]
})
export class LogoComponent {
  @Input() img = ''; // Valor padrão

  
}