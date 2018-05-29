import { Component, OnInit, Input } from '@angular/core';
import { Appareil_digestif } from '../_models';

@Component({
  selector: 'app-appareil-digestif',
  templateUrl: './appareil-digestif.component.html',
  styleUrls: ['./appareil-digestif.component.css']
})
export class AppareilDigestifComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  @Input() appareilDigestif: Appareil_digestif;
}
