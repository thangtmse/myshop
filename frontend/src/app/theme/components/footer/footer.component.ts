import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  public lat: number = 21.0452801;
  public lng: number = 105.8506632;
  public zoom: number = 12;

  constructor() { }

  ngOnInit() { }

  subscribe(){ }

}