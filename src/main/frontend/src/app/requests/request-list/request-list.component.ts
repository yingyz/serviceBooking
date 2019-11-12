import {Component, OnDestroy, OnInit} from '@angular/core';
import {RequestModel} from "../../models/request.model";
import {Subscription} from "rxjs";
import {RequestsService} from "../requests.service";
import {AuthService} from "../../auth/auth.service";
import {UserModel} from "../../models/user.model";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit, OnDestroy {
  user: UserModel;

  requests: RequestModel[] = [];
  private requestSub: Subscription;

  provideType: string = 'All';
  provideTypes: string[] = [];

  language: string = 'All';
  languages: string[] = [];

  page: number = 0;

  constructor(private requestService: RequestsService, private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = this.authService.getUser();

    this.requests = this.requestService.getRequests();
    this.requestService.getRequestsFromAPI(this.provideType, this.language);
    this.requestSub = this.requestService.getRequestsChanged()
      .subscribe(
        (requests: any[]) => {
          this.requests = requests;
        }
      );

    this.authService.getProvideTypes()
      .subscribe(
        provideTypes => {
          this.provideTypes = ['All', ...provideTypes];
        }
      );

    this.authService.getLanguages()
      .subscribe(
        languages => {
          this.languages = ['All', ...languages];
        }
      );
  }

  onSelect() {
    this.requestService.getRequestsFromAPI(this.provideType, this.language);
  }

  onNewRequest() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  ngOnDestroy(): void {
    this.requestSub.unsubscribe();
  }
}
