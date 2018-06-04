import { Component, OnInit, Input } from '@angular/core';
import { Appareil } from '../_models';

@Component({
  selector: 'app-appareil',
  templateUrl: './appareil.component.html',
  styleUrls: ['./appareil.component.css']
})

export class AppareilComponent{

 @Input()appareil: Appareil;

  constructor() { }

  ngOnInit() {
  }

}
