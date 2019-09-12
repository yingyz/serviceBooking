import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserModel} from "../auth/user.model";
import {AuthService} from "../auth/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {

  private userSub: Subscription;
  user: UserModel;
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userSub = this.authService.user
      .subscribe(
        (user : UserModel) => {
          this.user = user;
        }
      );
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }
}
