import { Component, OnInit } from '@angular/core';
import { Appareil, Appareil_digestif } from '../_models';
import { Tissu } from '../_models';
import { AppareilService } from '../_services/appareil.service';
import { TissuService } from '../_services/tissu.service';
import { AppareilDigestifService } from '../_services/appareil-digestif.service';


@Component({
  selector: 'app-encyclopedie',
  templateUrl: './encyclopedie.component.html',
  styleUrls: ['./encyclopedie.component.css']
})
export class EncyclopedieComponent implements OnInit {
  view_appareil = false;
  view_tissu = false;
  view_appareil_digestif = false;

  appareils;
  appareil = new Appareil('', '');

  tissus;
  tissu = new Tissu('','');

appareils_digestifs;
appareilDigestif = new Appareil_digestif('','');

  constructor(private appareilService: AppareilService, private tissuService : TissuService, 
  private appareilDigestifService : AppareilDigestifService) { }

  ngOnInit() {
    this.appareils = this.appareilService.getAppareilService();
    this.tissus = this.tissuService.getTissuService();
    this.appareils_digestifs = this.appareilDigestifService.getAppareilDigestifService();
  }

  visibleAppareil() {
    this.view_appareil =! this.view_appareil;
  }
  visibleTissu(){
    this.view_tissu =! this.view_tissu;
  }
  visibleAppareilDigestif(){
    this.view_appareil_digestif =! this.view_appareil_digestif;
  }
}