import { Injectable } from '@angular/core';
import { Element } from '../_models';
@Injectable({
  providedIn: 'root'
})
export class ElementService {
  elements = [new Element('Lèvres', 'Dig', ''),
  new Element('Gencives', 'Dig', ''),
  new Element('Dents', 'Dig', ''),
  new Element('Palais', 'Dig', ''),
  new Element('Langue', 'Dig', ''),
  new Element('Pharynx', 'Dig', ''),
  new Element('Oesophage', 'Dig', ''),
  new Element('Estomac', 'Dig', ''),
  new Element('Intestin grêle', 'Dig', ''),
  new Element('Gros Intestin', 'Dig', ''),
  new Element('Colon', 'Dig', ''),
  new Element('Vulve', 'Repro', ''),
  new Element('Vagin', 'Repro', ''),
  new Element('Utérus', 'Repro', ''),
  new Element('Trompe de Fallope', 'Repro', ''),
  new Element('Ovaires', 'Repro', ''),
  new Element('Glandes de Skene', 'Repro', ''),
  new Element('Glandes de Bartholin', 'Repro', ''),
  new Element('Pénis', 'Repro', ''),
  new Element('Testicules', 'Repro', ''),
  new Element('Prostate', 'Repro', ''),
  new Element('Vésicules séminales', 'Repro', ''),
  new Element('Epididymes', 'Repro', ''),
  new Element('Glande de Cowper', 'Repro', ''),



]
  constructor() { }

  getElementbyType() {
    return this.elements;
  }
}

