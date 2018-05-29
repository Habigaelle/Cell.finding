import { Component, OnInit, Input } from '@angular/core';
import { Tissu } from '../_models';

@Component({
  selector: 'app-tissu',
  templateUrl: './tissu.component.html',
  styleUrls: ['./tissu.component.css']
})
export class TissuComponent implements OnInit {

  @Input() tissu: Tissu;

  constructor() { }

  ngOnInit() {
  }

}
