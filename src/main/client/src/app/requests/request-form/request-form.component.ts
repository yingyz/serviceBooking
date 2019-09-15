import { Component, OnInit } from '@angular/core';
import {RequestsService} from "../requests.service";
import {ActivatedRoute, Params} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RequestModel} from "../request.model";

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {

  id: number;
  editMode = false;
  requestForm: FormGroup;
  Active = true;
  request: RequestModel;

  constructor(private requestService: RequestsService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params.id;
          this.editMode = params.id != null;
          this.initForm();
        }
      );
  }

  initForm() {
    let title = '';
    let info = '';

    if (this.editMode) {
      this.request = this.requestService.getRequest(this.id);
      this.Active = this.request.active;
      title = this.request.requestTitle;
      info = this.request.info;
    }

    this.requestForm = new FormGroup({
      title: new FormControl(title, Validators.required),
      info: new FormControl(info, Validators.required)
    });
  }

  onSubmit() {
      if (this.editMode) {
        this.requestService.updateRequest(this.request.requestId, this.id);
      } else {
        this.requestService.addRequest(this.requestForm.value.title, this.requestForm.value.info);
      }
  }

}
