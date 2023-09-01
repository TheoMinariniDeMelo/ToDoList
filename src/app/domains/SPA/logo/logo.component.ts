import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-logo',
<<<<<<< HEAD:src/app/logo/logo.component.ts
  // eslint-disable-next-line @angular-eslint/template/alt-text
  template: "<img src='assets/image-removebg-preview.png' />",        
=======
  template: "<img src='../../assets/image-removebg-preview.png' />",        
>>>>>>> a176854808433993aeb09551c51b2f9b85aaf62f:src/app/domains/SPA/logo/logo.component.ts
  styles: [`
    img {
      width: 70px;
    }
  `]
})
export class LogoComponent {
<<<<<<< HEAD:src/app/logo/logo.component.ts
  @Input() img = ''; // Valor padrão
  
=======
  @Input() img = ''; // Valor padrão 
>>>>>>> a176854808433993aeb09551c51b2f9b85aaf62f:src/app/domains/SPA/logo/logo.component.ts
}