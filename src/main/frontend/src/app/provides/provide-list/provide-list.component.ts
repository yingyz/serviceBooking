import { Component, OnInit } from '@angular/core';
import {ProvideModel} from "../provide.model";
import {Subject, Subscription} from "rxjs";

@Component({
  selector: 'app-provide-list',
  templateUrl: './provide-list.component.html',
  styleUrls: ['./provide-list.component.css']
})
export class ProvideListComponent implements OnInit {

  provides: ProvideModel[] = [];
  providesSub: Subscription;

  constructor() { }

  ngOnInit() {
  }

}
