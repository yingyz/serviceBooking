import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../admin.service";

@Component({
  selector: 'app-languages-form',
  templateUrl: './languages-form.component.html',
  styleUrls: ['./languages-form.component.css']
})
export class LanguagesFormComponent implements OnInit {

  languageForm: FormGroup;
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.languageForm = new FormGroup({
      name: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    this.adminService.addLanguage(this.languageForm.value.name);
  }
}
