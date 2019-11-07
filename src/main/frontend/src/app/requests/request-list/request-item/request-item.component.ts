import {Component, Input, OnInit} from '@angular/core';
import {RequestModel} from "../../../models/request.model";

@Component({
  selector: 'app-request-item',
  templateUrl: './request-item.component.html',
  styleUrls: ['./request-item.component.css']
})
export class RequestItemComponent implements OnInit {

  @Input() requestItem: RequestModel;
  @Input() index: number;

  constructor() { }

  ngOnInit() {
  }

}
