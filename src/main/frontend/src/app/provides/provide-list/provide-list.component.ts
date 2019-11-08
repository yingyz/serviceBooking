import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {ServiceProvideModel} from "../../models/serviceProvide.model";
import {UserModel} from "../../models/user.model";
import {ProvideService} from "../provide.service";

@Component({
  selector: 'app-provide-list',
  templateUrl: './provide-list.component.html',
  styleUrls: ['./provide-list.component.css']
})
export class ProvideListComponent implements OnInit {

  provides: ServiceProvideModel[] = [];
  providesSub: Subscription;
  provideType: string = null;

  constructor(private provideService: ProvideService) { }

  ngOnInit() {
    this.provideService.getProvidesFromAPI(null);
    this.providesSub = this.provideService.getProvidesChanged()
      .subscribe(
        provides => {
          this.provides = provides;
        }
      );
  }

  onSearchByName() {
    this.provideService.getProvidesFromAPI(this.provideType);
  }
}
