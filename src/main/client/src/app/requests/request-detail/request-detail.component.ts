import {Component,  OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {RequestsService} from "../requests.service";
import {RequestModel} from "../request.model";

@Component({
  selector: 'app-request-detail',
  templateUrl: './request-detail.component.html',
  styleUrls: ['./request-detail.component.css']
})
export class RequestDetailComponent implements OnInit {

  id: number;
  request: RequestModel;
  location: string;

  constructor(private route: ActivatedRoute, private requestService: RequestsService, private router: Router) { }

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
  }

  onEditRequest() {
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

}
