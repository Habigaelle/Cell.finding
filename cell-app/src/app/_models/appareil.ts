export class Appareil {
    public  nom: string;
    static count =  0;
    public id = 0 ;
  
      constructor(nom ='', id) {
        this.nom = nom;
        this.id = Appareil.count ++; 
      }
  
      
  }