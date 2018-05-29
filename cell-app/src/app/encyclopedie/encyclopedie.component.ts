import { Component, OnInit } from '@angular/core';
import { Appareil } from '../_models';
import { AppareilService } from '../_services/appareil.service';


@Component({
  selector: 'app-encyclopedie',
  templateUrl: './encyclopedie.component.html',
  styleUrls: ['./encyclopedie.component.css']
})
export class EncyclopedieComponent implements OnInit {
  edit = false;

  appareilSelected: Appareil;
  appareils;
  appareil = new Appareil();

  constructor(private appareilService: AppareilService) { }
  
  ngOnInit() {
    this.appareils = this.appareilService.getAppareilService();
  }
visible(){
  this.edit !=this.edit;
}
select(e: Appareil) {
  this.appareilSelected = e;
}
}