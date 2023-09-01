import { TestBed } from '@angular/core/testing';

import { ConfirmDialogServiceRegister } from './confirm-dialog.service';

describe('ConfirmDialogService', () => {
  let service: ConfirmDialogServiceRegister;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfirmDialogServiceRegister);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
