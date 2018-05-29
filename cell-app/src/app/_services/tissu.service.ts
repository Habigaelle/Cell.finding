import { Injectable } from '@angular/core';
import { Tissu } from '../_models';

@Injectable({
  providedIn: 'root'
})
export class TissuService {
  tissus= [  new Tissu('Tissus conjonctifs et de soutien',''),
  new Tissu('Tissus épithéliaux',''),
  new Tissu('Tissus musculaires',''),
  new Tissu('Tissus nerveux','')]


  constructor() { }

  getTissuService(){
    return this.tissus;
  }
}
