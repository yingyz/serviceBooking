import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";

@Injectable()
export class ProvideGuard implements CanActivate{
  constructor(private authService: AuthService, private router: Router){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const isAuth = this.authService.getIsAuth();
    if (!isAuth) {
      this.router.navigate(['/auth/login']);
      return false;
    }
    const isAdmin = this.authService.getUser().role == 'Service';
    if (!isAdmin) {
      this.router.navigate(['/dashboard/profile']);
      return false;
    }
    return true;
  }
}
