import { TestBed, inject } from '@angular/core/testing';

import { AppareilService } from './appareil.service';

describe('AppareilService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppareilService]
    });
  });

  it('should be created', inject([AppareilService], (service: AppareilService) => {
    expect(service).toBeTruthy();
  }));
});
