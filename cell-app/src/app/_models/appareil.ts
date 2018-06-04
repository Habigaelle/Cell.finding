import { Element } from '../_models';


export class Appareil {
    public  nom: string;
    public Element ;
    static count =  0;
    public id = 0 ;
  
      constructor(nom ='', id,) {
        this.nom = nom;
        this.id = Appareil.count ++; 
      }
  
      
  }