import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../admin.service";

@Component({
  selector: 'app-serviceform',
  templateUrl: './serviceform.component.html',
  styleUrls: ['./serviceform.component.css']
})
export class ServiceformComponent implements OnInit {

  serviceForm: FormGroup;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.serviceForm = new FormGroup({
      name: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    this.adminService.addService(this.serviceForm.value.name);
  }
}
