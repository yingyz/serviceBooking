import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {AuthService} from "../../auth/auth.service";
import {UserModel} from "../../auth/user.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit, OnDestroy {

  profileForm: FormGroup;
  id: number;
  user: UserModel;
  private userSub: Subscription;

  constructor(private router: Router, private authService: AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.userSub = this.authService.user.subscribe((user: UserModel) => {
      this.user = user;
      this.initForm();
    });
  }

  initForm() {

    let firstname = this.user.firstname;
    let lastname = this.user.lastname;
    let streetname = this.user.streetname;
    let city = this.user.city;
    let state = this.user.state;
    let zipcode = this.user.zipcode;
    let phone = this.user.phone;

    this.profileForm = new FormGroup({
      firstname: new FormControl(firstname, Validators.required),
      lastname: new FormControl(lastname, Validators.required),
      streetname: new FormControl(streetname, Validators.required),
      city: new FormControl(city, Validators.required),
      state : new FormControl(state, Validators.required),
      zipcode: new FormControl(zipcode, Validators.required),
      phone: new FormControl(phone, Validators.required)
    });
  }

  onSubmit() {
    this.authService.updateProfile(
      this.profileForm.value.firstname,
      this.profileForm.value.lastname,
      this.profileForm.value.streetname,
      this.profileForm.value.city,
      this.profileForm.value.state,
      this.profileForm.value.zipcode,
      this.profileForm.value.phone
    );
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }

}
