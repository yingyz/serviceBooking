import { Component, OnInit } from '@angular/core';
import {RequestModel} from "../../models/request.model";
import {RequestsService} from "../requests.service";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-request-detail',
  templateUrl: './request-detail.component.html',
  styleUrls: ['./request-detail.component.css']
})
export class RequestDetailComponent implements OnInit {

  index: number;
  request: RequestModel;
  location: string;
  fullName: string

  constructor(private requestService: RequestsService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.index = +params.id;
          this.request = this.requestService.getRequestyIdx(this.index);
          this.location = this.request.userDto.streetname + ", " + this.request.userDto.city + ", " + this.request.userDto.state
            + ", " + this.request.userDto.zipcode;
          this.fullName = this.request.userDto.firstname + " " + this.request.userDto.lastname;
        }
      );
  }

  onEditRequest(){
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

}
