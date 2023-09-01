import { Injectable } from '@angular/core';
import { ConfirmationService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ConfirmDialogServiceRegister {
  constructor(protected confirmationService: ConfirmationService){}

  confirmation(acceptFunc: () => void, rejectFunc: () => void) {
    this.confirmationService.confirm({
      message: 'Se confirmar, será redirecionado para a página de login',
      header: 'Você confirma as credenciais inseridas?',
      icon: '',
      acceptLabel:"Aceito",
      rejectLabel:"Rejeito",
      accept: acceptFunc,
      reject: rejectFunc
    });
  }
}
