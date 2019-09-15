import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-authheader',
  templateUrl: './authheader.component.html',
  styleUrls: ['./authheader.component.css']
})
export class AuthheaderComponent implements OnInit, OnDestroy {

  userRole: string;
  private userSub: Subscription;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userSub = this.authService.user.subscribe(
      user => {
        if (user != null) this.userRole = user.userRole;
      }
    );
  }

  onLogout() {
    this.authService.logout();
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }
}
