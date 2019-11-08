import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";
import {AdminService} from "../admin.service";

@Component({
  selector: 'app-roles',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: UserModel[] = [];
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getUsers().subscribe(
      (users: any[]) => {
        this.users = users;
      }
    );
  }

}
