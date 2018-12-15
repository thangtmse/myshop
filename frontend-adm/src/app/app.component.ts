import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { map, mergeMap, filter } from "rxjs/operators";
import { AuthenticationService } from './service/authentication.service';
import { SharedService } from './service/shared.service';
declare var window: Window;

@Component({
  selector: 'body',
  template: '<router-outlet></router-outlet>'
})
export class AppComponent implements OnInit {
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private titleService: Title,
    private authService: AuthenticationService
  ) { }
  ngOnInit() {
    this.router.events
      .pipe(
        filter((event) => event instanceof NavigationEnd)
      )
      .pipe(
        map(() => this.activatedRoute)
      )
      .pipe(
        map((route) => {
          while (route.firstChild) route = route.firstChild;
          return route;
        })
      )
      .pipe(
        filter((route) => route.outlet === 'primary')
      )
      .pipe(
        mergeMap((route) => route.data)
      )
      .subscribe((event) => {
        if (event['title']) {
          this.titleService.setTitle(event['title']);
        }
      });
    SharedService.userLogin.subscribe(() => {
      this.authService.getProfile().subscribe(data => {
        this.initRealtime();
        data ? (data.role ? (data.role.id == 3 ? this.initNotiForChef() : '') : '') : '';
        data ? (data.role ? (data.role.id == 4 ? this.initNotiForWaiter() : '') : '') : '';
      })
    })
  }
  initRealtime(){
    
  }
  initNotiForChef() {
    }

  initNotiForWaiter() {
    
  }
}
interface Window {
  showNotification?: any;
  pusher?: any
}