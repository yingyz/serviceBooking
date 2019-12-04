import { Component, OnInit } from '@angular/core';
import {ServiceProvideModel} from "../../models/serviceProvide.model";
import {ProvideService} from "../provide.service";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  selector: 'app-provide-detail',
  templateUrl: './provide-detail.component.html',
  styleUrls: ['./provide-detail.component.css']
})
export class ProvideDetailComponent implements OnInit {

  index: number;
  provide: ServiceProvideModel;
  location: string;
  fullName: string;

  constructor(private provideService: ProvideService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          let ids = params.id.split('|');
          this.index = +ids[0];
          this.provide = this.provideService.getProvideByIdx(this.index);
          this.location = this.provide.userDto.streetname + ", " + this.provide.userDto.city + ", " + this.provide.userDto.state
            + ", " + this.provide.userDto.zipcode;
          this.fullName = this.provide.userDto.firstname + " " + this.provide.userDto.lastname;
        }
      );
  }

}
