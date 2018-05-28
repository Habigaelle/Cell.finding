import { Component, OnInit } from '@angular/core';
import { Appareil } from '../_models';
import { AppareilService } from '../_services/appareil.service';


@Component({
  selector: 'app-cell-finding',
  templateUrl: './cell-finding.component.html',
  styleUrls: ['./cell-finding.component.css']
})
export class EncyclopedieComponent implements OnInit {
  edit = false;

  appareilSelected: Appareil;
  appareils;
  appareil = new Appareil();

  constructor(private appareilService: AppareilService) { }

  ngOnInit() {

  }
}