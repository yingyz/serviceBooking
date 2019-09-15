import {Component, OnDestroy, OnInit} from '@angular/core';
import {RequestsService} from "../requests.service";
import {RequestModel} from "../request.model";
import {AuthService} from "../../auth/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit, OnDestroy {

  requests: RequestModel[] = [];
  userRole: string;
  private userSub: Subscription;
  private requestSub: Subscription;

  constructor(private requestService: RequestsService, private authService: AuthService) { }

  ngOnInit() {
    this.userSub = this.authService.user.subscribe( user => {
        if (user !== null) {
          this.userRole = user.userRole;

          this.requestService.getRequests(this.userRole);
        }
      }
    );

    this.requestSub = this.requestService.getRequestUpdateListener()
      .subscribe(requests => {
        this.requests = requests;
      });
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
    this.requestSub.unsubscribe();
  }
}
