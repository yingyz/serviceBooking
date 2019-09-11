import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-authheader',
  templateUrl: './authheader.component.html',
  styleUrls: ['./authheader.component.css']
})
export class AuthheaderComponent implements OnInit {


  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onLogout() {
    this.authService.logout();
  }

}
