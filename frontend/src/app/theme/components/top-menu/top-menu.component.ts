import { Component, OnInit } from '@angular/core';
import { Data, AppService } from '../../../app.service';
import { User } from '../../../app.models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html'
})
export class TopMenuComponent implements OnInit {
  public currencies = ['USD', 'EUR'];
  public currency:any;
  public userInfo:User[];
  public flags = [
    { name:'English', image: 'assets/images/flags/gb.svg' },
    { name:'German', image: 'assets/images/flags/de.svg' },
    { name:'French', image: 'assets/images/flags/fr.svg' },
    { name:'Russian', image: 'assets/images/flags/ru.svg' },
    { name:'Turkish', image: 'assets/images/flags/tr.svg' }
  ]
  public flag:any;

  constructor(public appService:AppService,private router: Router) { }

  ngOnInit() {
    this.currency = this.currencies[0];
    this.flag = this.flags[0];
    console.log('start get user info');
    this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
    console.log(this.userInfo);
  }

  public changeCurrency(currency){
    this.currency = currency;
  }

  public changeLang(flag){
    this.flag = flag;
  }

  public signOut(){
    this.userInfo = null;
    localStorage.setItem('userInfo',null);
    console.log("xoa user Info thanh cong")
    this.router.navigate(['/sign-in']);
  }

  

}
