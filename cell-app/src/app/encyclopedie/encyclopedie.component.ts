import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-encyclopedie',
  templateUrl: './encyclopedie.component.html',
  styleUrls: ['./encyclopedie.component.css']
})
export class EncyclopedieComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  visible = true;
  liste_visible(id:string){
    this.visible != this.visible;
  }
}
