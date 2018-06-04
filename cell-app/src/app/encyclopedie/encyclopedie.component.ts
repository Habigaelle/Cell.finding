import { Component, OnInit } from '@angular/core';
import { Appareil } from '../_models';
import { Tissu } from '../_models';
import { AppareilService } from '../_services/appareil.service';
import { TissuService } from '../_services/tissu.service';
import { ElementService } from '../_services/element.service';

@Component({
  selector: 'app-encyclopedie',
  templateUrl: './encyclopedie.component.html',
  styleUrls: ['./encyclopedie.component.css']
})
export class EncyclopedieComponent implements OnInit {
  view_appareil = false;
  view_tissu = false;
  view_appareilDig = false;

  elements;
 


  appareils;
  appareil = new Appareil('', '');

  tissus;
  tissu = new Tissu('', '');

  constructor(private appareilService: AppareilService,
    private tissuService: TissuService ,
    private elementService : ElementService
  ) { }

  ngOnInit() {
    this.appareils = this.appareilService.getAppareilService();
    this.tissus = this.tissuService.getTissuService();
    this.elements = this.elementService.getElementbyType();
  }

  visibleAppareil() {
    this.view_appareil = !this.view_appareil;
  } 

  visibleTissu() {
    this.view_tissu = !this.view_tissu;
  } 

  visibleADig() {
    this.view_appareilDig = !this.view_appareilDig;
  } 
}