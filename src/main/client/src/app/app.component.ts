import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "./auth/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'client';
  isAuthenticated = false;
  private userSub: Subscription;

  constructor(private authService: AuthService){}

  ngOnInit(){
    this.isAuthenticated = this.authService.getIsAuth();
    this.userSub = this.authService.getAuthStatusListener()
      .subscribe(
        authStatus => {
          this.isAuthenticated = authStatus;
        }
      );
  }

  ngOnDestroy(){
    this.userSub.unsubscribe();
  }
}



