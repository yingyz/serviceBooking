import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {Subscription} from "rxjs";
import {UserModel} from "../../models/user.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  private authListenerSubs: Subscription;
  user: UserModel;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.user = this.authService.getUser();
    this.authListenerSubs = this.authService.getAuthStatusListener()
      .subscribe(
        response => {
          this.user = this.authService.getUser();
        }
      );
  }

  ngOnDestroy(): void {
    this.authListenerSubs.unsubscribe();
  }
}
