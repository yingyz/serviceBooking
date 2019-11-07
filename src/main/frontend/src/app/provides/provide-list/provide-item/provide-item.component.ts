import {Component, Input, OnInit} from '@angular/core';
import {ServiceProvideModel} from "../../../models/serviceProvide.model";

@Component({
  selector: 'app-provide-item',
  templateUrl: './provide-item.component.html',
  styleUrls: ['./provide-item.component.css']
})
export class ProvideItemComponent implements OnInit {

  @Input() provideItem: ServiceProvideModel;
  @Input() index: number;
  constructor() { }

  ngOnInit() {
  }

}
