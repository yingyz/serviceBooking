import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserModel} from "../../auth/user.model";
import {Subscription} from "rxjs";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})

export class EditProfileComponent implements OnInit, OnDestroy {

  private userSub: Subscription;
  user: UserModel;
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userSub = this.authService.user
      .subscribe(
        (user : UserModel) => {
          this.user = user;
        }
      );
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }

}
