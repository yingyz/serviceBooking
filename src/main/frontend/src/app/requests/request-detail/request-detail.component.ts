import { Component, OnInit } from '@angular/core';
import {RequestModel} from "../../models/request.model";
import {RequestsService} from "../requests.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {UserModel} from "../../models/user.model";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-request-detail',
  templateUrl: './request-detail.component.html',
  styleUrls: ['./request-detail.component.css']
})
export class RequestDetailComponent implements OnInit {

  index: number;
  request: RequestModel;
  location: string;
  fullName: string;
  user: UserModel;
  editAuth = false;
  userRole: string;

  constructor(private requestService: RequestsService, private route: ActivatedRoute, private router: Router, private authService: AuthService) { }

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
    this.user = this.authService.getUser();
    if (this.user != null && this.request != null) {
      this.userRole = this.user.role;
      this.editAuth = this.request.userDto.username === this.user.username;
    }
  }

  onEditRequest(){
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

}
