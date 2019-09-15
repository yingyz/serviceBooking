import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {RequestsService} from "../requests.service";
import {RequestModel} from "../request.model";
import {AuthService} from "../../auth/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-request-detail',
  templateUrl: './request-detail.component.html',
  styleUrls: ['./request-detail.component.css']
})
export class RequestDetailComponent implements OnInit, OnDestroy {

  id: number;
  request: RequestModel;
  location: string;
  editAuth = false;
  private userSub: Subscription;

  constructor(private route: ActivatedRoute, private requestService: RequestsService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params.id;
          this.request = this.requestService.getRequest(this.id);
          this.location = this.request.User.streetname + ", " + this.request.User.city + ", " + this.request.User.state
            + ", " + this.request.User.zipcode;
        }
      );

    this.userSub = this.authService.user.subscribe(user => {
      this.editAuth = this.request.User.userName === user.userName;
    });
  }

  onEditRequest() {
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }
}
