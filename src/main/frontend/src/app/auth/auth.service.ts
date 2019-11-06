import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {UserModel} from "../models/user.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RegisterDataModel} from "./register-data.model";

const BACKEND_URL = environment.apiUrl + '/users/';

@Injectable({providedIn: "root"})
export class AuthService {
  private isAuthenticated = false;
  private authStatusListener = new Subject<boolean>();
  private token: string = '';
  private user: UserModel = null;

  constructor(private http: HttpClient){}

  getToken() {
    return this.token;
  }

  getIsAuth() {
    return this.isAuthenticated;
  }

  getAuthStatusListener() {
    return this.authStatusListener.asObservable();
  }

  gerUser() {
    return this.user;
  }

  login(username: string, password: string) {
    const authData = {username: username, password: password};
    this.http.post<{token: string, user: UserModel}>(BACKEND_URL + 'login', authData)
      .subscribe(
        response => {
          this.token = response.token;
          this.user = response.user;
          this.authStatusListener.next(true);
          console.log(response);
        },
        error => {
          console.log(error);
          this.authStatusListener.next(false);
        }
      );
  }

  logout() {
    this.user = null;
    this.authStatusListener.next(false);
  }

  signup(registerData: RegisterDataModel) {
    this.http.post<{message: string}>(BACKEND_URL + 'register', registerData)
      .subscribe(
        response => {
          console.log(response.message);
        },
        error => {
          console.log(error);
          this.authStatusListener.next(false);
        }
      );
  }
}
