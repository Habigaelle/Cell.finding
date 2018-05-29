import { TestBed, inject } from '@angular/core/testing';

import { TissuService } from './tissu.service';

describe('TissuService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TissuService]
    });
  });

  it('should be created', inject([TissuService], (service: TissuService) => {
    expect(service).toBeTruthy();
  }));
});
