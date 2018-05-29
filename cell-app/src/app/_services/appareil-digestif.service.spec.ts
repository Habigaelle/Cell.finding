import { TestBed, inject } from '@angular/core/testing';

import { AppareilDigestifService } from './appareil-digestif.service';

describe('AppareilDigestifService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppareilDigestifService]
    });
  });

  it('should be created', inject([AppareilDigestifService], (service: AppareilDigestifService) => {
    expect(service).toBeTruthy();
  }));
});
