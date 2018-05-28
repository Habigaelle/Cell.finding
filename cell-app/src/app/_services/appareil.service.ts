import { Injectable } from '@angular/core';
import { Appareil } from '../_models';

@Injectable({
  providedIn: 'root'
})

export class AppareilService {
  appareils = [new Appareil('Appareil Digestif'),
  new Appareil('Appareil Reproducteur'),
  new Appareil('Appareil Respiratoire'),
  new Appareil('Appareil Urinaire'),
  new Appareil('Squelette'),
  new Appareil('Système Cardiovasculaire'),
  new Appareil('Système Musculaire'),
  new Appareil('Système Immunitaire'),
  new Appareil('Système Nerveux'),]

  constructor() { }

}
