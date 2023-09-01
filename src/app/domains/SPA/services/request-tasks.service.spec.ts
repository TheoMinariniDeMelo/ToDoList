import { TestBed } from '@angular/core/testing';

import { RequestTasksService } from './request-tasks.service';

describe('RequestTasksService', () => {
  let service: RequestTasksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RequestTasksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
