import { Component, OnInit } from '@angular/core';
import {RequestsService} from "../requests.service";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {

  id: number;
  editMode = false;

  constructor(private requestService: RequestsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params.id;
          this.editMode = params.id != null;
        }
      );
  }

}
