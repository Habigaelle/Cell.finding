import { TestBed, inject } from '@angular/core/testing';

import { AppareilReproducteurService } from './appareil-reproducteur.service';

describe('AppareilReproducteurService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppareilReproducteurService]
    });
  });

  it('should be created', inject([AppareilReproducteurService], (service: AppareilReproducteurService) => {
    expect(service).toBeTruthy();
  }));
});
