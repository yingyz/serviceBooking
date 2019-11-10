import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {UserModel} from "../models/user.model";

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  user: UserModel;

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {
    this.user = this.authService.getUser();
  }

  onNewRequest() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

}
