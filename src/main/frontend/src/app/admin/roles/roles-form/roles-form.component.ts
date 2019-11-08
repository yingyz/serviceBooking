import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../admin.service";

@Component({
  selector: 'app-roles-form',
  templateUrl: './roles-form.component.html',
  styleUrls: ['./roles-form.component.css']
})
export class RolesFormComponent implements OnInit {

  roleForm: FormGroup;
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.roleForm = new FormGroup({
      name: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    this.adminService.addRole(this.roleForm.value.name);
  }
}
