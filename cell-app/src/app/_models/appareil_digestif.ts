export class Appareil_digestif{
    public  nom: string;
    static count =  0;
    public id = 0 ;
  
      constructor(nom ='', id) {
        this.nom = nom;
        this.id = Appareil_digestif.count ++; 
      } 
  }