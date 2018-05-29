import { Injectable } from '@angular/core';
import { Appareil_reproducteur } from '../_models/appareil_reproducteur';

@Injectable({
  providedIn: 'root'
})
export class AppareilReproducteurService {
appareils_reproducteurs = [new Appareil_reproducteur('Glandes de Bartholin',''),
new Appareil_reproducteur('Glandes de Skene',''),
new Appareil_reproducteur('Ovaires',''),
new Appareil_reproducteur('Trompes de Fallope',''),
new Appareil_reproducteur('Utérus',''),
new Appareil_reproducteur('Vagin',''),
new Appareil_reproducteur('Vulve',''),
new Appareil_reproducteur('Epididymes',''),
new Appareil_reproducteur('Glandes de Cowper',''),
new Appareil_reproducteur('Pénis',''),
new Appareil_reproducteur('Prostate',''),
new Appareil_reproducteur('Testicules',''),
new Appareil_reproducteur('Vésicules Séminales','')
]
  constructor() { }
  
  getAppareilReproducteurService() {
    return this.appareils_reproducteurs;
  }
}
