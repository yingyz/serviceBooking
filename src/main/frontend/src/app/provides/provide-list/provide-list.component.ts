import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {ServiceProvideModel} from "../../models/serviceProvide.model";
import {UserModel} from "../../models/user.model";

@Component({
  selector: 'app-provide-list',
  templateUrl: './provide-list.component.html',
  styleUrls: ['./provide-list.component.css']
})
export class ProvideListComponent implements OnInit {

  provides: ServiceProvideModel[] = [
    new ServiceProvideModel(1, "Detail", "10", "House Moving", new UserModel(4,"Henry","Henry","Liu","street","city","CA",92831,"110","English","Service"))
  ];
  providesSub: Subscription;

  constructor() { }

  ngOnInit() {
  }

}
