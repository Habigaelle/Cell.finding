export class Element {
    public  nom: string;
    public type : string;
    static count =  0;
    public id = 0 ;
  
      constructor(nom ='',type='', id,) {
        this.nom = nom;
        this.type = type;
        this.id = Element.count ++; 
      }
  

    
  }