import {Component, Input, OnInit} from '@angular/core';
import {RequestModel} from "../../../models/request.model";

@Component({
  selector: 'app-provide-item',
  templateUrl: './provide-item.component.html',
  styleUrls: ['./provide-item.component.css']
})
export class ProvideItemComponent implements OnInit {

  @Input() provideItem: RequestModel;
  @Input() index: number;
  constructor() { }

  ngOnInit() {
  }

}
