import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../auth.service";
import {RegisterDataModel} from "../register-data.model";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const password = form.value.password;
    const firstname = form.value.firstname;
    const lastname = form.value.lastname;
    const streetname = form.value.streetname;
    const city = form.value.city;
    const state = form.value.state;
    const zipcode = form.value.zipcode;
    const phone = form.value.phone;
    const role = form.value.role;
    const language = form.value.language;

    const registerData = new RegisterDataModel(
      email,
      password,
      city,
      firstname,
      lastname,
      streetname,
      state,
      zipcode,
      phone,
      language,
      role
    );
    //console.log(registerData);
    this.authService.signup(registerData);
  }
}
