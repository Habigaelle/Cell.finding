import { Injectable } from '@angular/core';
import { Appareil_digestif } from '../_models';

@Injectable({
  providedIn: 'root'
})
export class AppareilDigestifService {
appareils_digestifs=
[new Appareil_digestif('Lèvres',''),
new Appareil_digestif('Dents',''),
new Appareil_digestif('Gencives',''),
new Appareil_digestif('Palais',''),
new Appareil_digestif('Glandes salivaires',''),
new Appareil_digestif('Lèvres',''),
new Appareil_digestif('Pharynx',''),
new Appareil_digestif('Oesophage',''),
new Appareil_digestif('Estomac',''),
new Appareil_digestif('Foie et vésicule biliaire',''),
new Appareil_digestif('Pancréas',''),
new Appareil_digestif('Intestin Grêle',''),
new Appareil_digestif('Gros Intestin',''),
]
  constructor() { }

  getAppareilDigestifService() {
    return this.appareils_digestifs;
  }
}
