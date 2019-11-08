import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {Subscription} from "rxjs";
import {UserModel} from "../../models/user.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ProvideService} from "../../provides/provide.service";
import {ServiceProvideModel} from "../../models/serviceProvide.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  private authListenerSubs: Subscription;
  user: UserModel;
  myProvide: ServiceProvideModel;
  private provideListenerSubs: Subscription;

  constructor(private authService: AuthService, private router: Router, private provideService: ProvideService) { }

  ngOnInit() {
    this.user = this.authService.getUser();
    this.myProvide = this.provideService.getMyProvide();
    this.authListenerSubs = this.authService.getAuthStatusListener()
      .subscribe(
        response => {
          this.user = this.authService.getUser();
          this.myProvide = this.provideService.getMyProvide();
        }
      );
    this.provideListenerSubs = this.provideService.getProvideStatusListener()
      .subscribe(
        provide => {
          this.myProvide = provide;
        }
      );
  }

  onRequests() {
    this.router.navigateByUrl('/requests');
  }

  onServiceUpdate() {
    this.router.navigateByUrl('/dashboard/editService');
  }

  ngOnDestroy(): void {
    this.authListenerSubs.unsubscribe();
    this.provideListenerSubs.unsubscribe();
  }
}
