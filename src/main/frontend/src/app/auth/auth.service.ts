import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {UserModel} from "../models/user.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RegisterDataModel} from "./register-data.model";
import {ActivatedRoute, Router} from "@angular/router";
import {UserInfoModel} from "../dashboard/UserInfo.model";
import {ProvideService} from "../provides/provide.service";
import {map} from "rxjs/operators";

const BACKEND_URL = environment.apiUrl + '/users/';

@Injectable({providedIn: "root"})
export class AuthService {
  private isAuthenticated: boolean = false;
  private authStatusListener = new Subject<boolean>();
  private token: string = '';
  private user: UserModel = null;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute, private provideService: ProvideService){}

  getToken() {
    return this.token;
  }

  getIsAuth() {
    return this.isAuthenticated;
  }

  getAuthStatusListener() {
    return this.authStatusListener.asObservable();
  }

  getUser() {
    return this.user;
  }

  login(username: string, password: string) {
    const authData = {username: username, password: password};
    this.http.post<{token: string, user: UserModel}>(BACKEND_URL + 'login', authData)
      .subscribe(
        response => {
          this.token = response.token;
          this.user = response.user;
          this.isAuthenticated = true;
          if (this.user.role == 'Service') {
            this.provideService.getMyProvideFromAPI();
          }
          this.authStatusListener.next(this.isAuthenticated);
          this.router.navigate(['/dashboard/profile'], {relativeTo: this.route});
        },
        error => {
          console.log(error);
          this.authStatusListener.next(false);
        }
      );
  }

  logout() {
    this.user = null;
    this.isAuthenticated = false;
    this.provideService.clearMyProvide();
    this.authStatusListener.next(this.isAuthenticated);
    this.router.navigate(["/auth/login"]);
  }

  signup(registerData: RegisterDataModel) {
    this.http.post<{message: string}>(BACKEND_URL + 'register', registerData)
      .subscribe(
        response => {
          console.log(response.message);
          this.router.navigate(["/auth/login"]);
        },
        error => {
          console.log("Error");
          this.authStatusListener.next(false);
        }
      );
  }

  updateProfile(profileData: UserInfoModel) {
    this.http.put<UserModel>('http://localhost:8080/api/userinfo', profileData)
      .subscribe(
        user => {
          this.user = user;
          this.authStatusListener.next(true);
          this.router.navigate(['/dashboard/profile'], {relativeTo: this.route});
        }
      );
  }

  getRoles() {
    return this.http.get(BACKEND_URL + 'role')
      .pipe(
        map(
          (roles: any[]) => {
            return roles.map(
              role => {
                return role.name;
              }
            );
          }
        )
      );
  }

  getProvideTypes() {
    return this.http.get(BACKEND_URL + 'serviceType')
      .pipe(
        map(
          (provideTypes: any[]) => {
            return provideTypes.map(
              provideType => {
                return provideType.name;
              }
            );
          }
        )
      );
  }
}
