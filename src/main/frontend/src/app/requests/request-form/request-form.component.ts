import { Component, OnInit } from '@angular/core';
import {RequestsService} from "../requests.service";
import {ActivatedRoute, Params} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RequestModel} from "../../models/request.model";

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {
  index: number;
  editMode = false;
  requestForm: FormGroup;
  request: RequestModel;

  constructor(private requestService: RequestsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.index = +params.id;
          this.editMode = params.id != null;
          this.initForm();
        }
      );
  }

  initForm() {
    let title = '';
    let info = '';

    if (this.editMode) {
      this.request = this.requestService.getRequestyIdx(this.index);
      title = this.request.title;
      info = this.request.info;
    }

    this.requestForm = new FormGroup({
      title: new FormControl(title, Validators.required),
      info: new FormControl(info, Validators.required)
    });
  }

  onSubmit() {
    if (this.editMode) {
      this.requestService.updateRequest(this.requestForm.value.title, this.requestForm.value.info, this.index);
    } else {
      this.requestService.addRequest(this.requestForm.value.title, this.requestForm.value.info);
    }
  }
}
