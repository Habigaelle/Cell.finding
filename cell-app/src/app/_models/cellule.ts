export class Cellule {
    public  nom: string;
    static count =  0;
    public id = 0 ;
  
      constructor(nom ='', id,) {
        this.nom = nom;
        this.id = Cellule.count ++; 
      }
  
      
  }