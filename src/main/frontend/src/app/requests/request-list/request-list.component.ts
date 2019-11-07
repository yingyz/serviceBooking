import {Component, OnDestroy, OnInit} from '@angular/core';
import {RequestModel} from "../../models/request.model";
import {Subscription} from "rxjs";
import {RequestsService} from "../requests.service";

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit, OnDestroy {
  requests: RequestModel[] = [];
  private requestSub: Subscription;

  constructor(private requestService: RequestsService) { }

  ngOnInit() {
    this.requestService.getRequestsFromAPI();
    this.requestSub = this.requestService.getRequestsChanged()
      .subscribe(
        requests => {
          this.requests = requests;
        }
      );
  }

  ngOnDestroy(): void {
    this.requestSub.unsubscribe();
  }
}
