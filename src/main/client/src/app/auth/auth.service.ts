import {Injectable} from "@angular/core";
import {BehaviorSubject, Subject} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {LoginData} from "./login/login-data.model";
import {environment} from "../../environments/environment";
import {UserModel} from "./user.model";

const BACKEND_URL = environment.apiUrl + '/users/';

@Injectable({providedIn: 'root'})
export class AuthService {
  private isAuthenticated = false;
  private authStatusListener = new Subject<boolean>();
  private token: string = '';
  user = new BehaviorSubject<UserModel>(null);

  getToken(){return this.token;}

  getIsAuth(){return this.isAuthenticated;}


  getAuthStatusListener() {
    return this.authStatusListener.asObservable();
  }

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute){}

  login(email: string, password: string) {
    const authData: LoginData = { username: email, password: password };
    this.http.post<{token: string, user: any}>(BACKEND_URL + 'login', authData)
      .subscribe(
        response => {
          this.token = response.token;
          const user = new UserModel(
            response.user.id,
            response.user.username,
            response.user.userInfo.firstname,
            response.user.userInfo.lastname,
            response.user.userInfo.streetname,
            response.user.userInfo.city,
            response.user.userInfo.state,
            response.user.userInfo.zipcode,
            response.user.userInfo.phone,
            response.user.fullName,
            response.user.role.name
          );
          this.user.next(user);
          this.authStatusListener.next(true);
          this.router.navigate(['/requests'], {relativeTo: this.route});
        },
        error => {
          console.log(error);
          this.authStatusListener.next(false);
        }
      );
  }

  logout() {
    this.authStatusListener.next(false);
    this.user.next(null);
    this.router.navigate(['/auth/login']);
  }

  updateProfile(firstname: string, lastname: string, streetname: string, city: string, state: string, zipcode: string, phone: string) {
    const profileData = {firstname: firstname, lastname: lastname, streetname: streetname, city: city, state: state, zipcode: zipcode, phone: phone};
    this.http.put<{id: number, username: string, userInfo: any, fullName: string, role: any}>('http://localhost:8080/api/userinfo', profileData)
      .subscribe(response => {

        const user = new UserModel(
          response.id,
          response.username,
          response.userInfo.firstname,
          response.userInfo.lastname,
          response.userInfo.streetname,
          response.userInfo.city,
          response.userInfo.state,
          response.userInfo.zipcode,
          response.userInfo.phone,
          response.fullName,
          response.role.name
        );
        this.user.next(user);

        this.router.navigate(['dashboard']);
      });
  }
}
