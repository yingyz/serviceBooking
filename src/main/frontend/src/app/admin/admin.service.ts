import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";

const BACKEND_URL = environment.apiUrl + '/admin/';
@Injectable({providedIn: 'root'})
export class AdminService {
  serviceTypes: any[] = [];
  serviceTypesChanged = new Subject<any[]>();

  roles: any[] = [];
  rolesChanged = new Subject<any[]>();

  constructor(private http: HttpClient){}

  getServiceTypes() {
    return this.serviceTypes;
  }

  getServiceStatusListener() {
    return this.serviceTypesChanged.asObservable();
  }

  getUsers() {
    return this.http.get(BACKEND_URL + 'user');
  }

  getRoles() {
    return this.roles;
  }

  getRolesStatusListener() {
    return this.rolesChanged.asObservable();
  }

  getRolesFromAPI() {
    this.http.get(BACKEND_URL + 'role')
      .subscribe(
        (roles: any[]) => {
          this.roles = roles;
          this.rolesChanged.next([...this.roles]);
        }
      );
  }

  getServiceTypesFromAPI() {
    this.http.get(BACKEND_URL + 'serviceType')
      .subscribe(
        (serviceTypes: any[]) => {
          this.serviceTypes = serviceTypes;
          this.serviceTypesChanged.next([...this.serviceTypes]);
        }
      );
  }

  addService(name: string) {
    this.http.post(BACKEND_URL + 'serviceType', {name : name})
      .subscribe(
        (serviceType: any) => {
          this.serviceTypes.push(serviceType);
          this.serviceTypesChanged.next([...this.serviceTypes]);
        }
      );
  }

  addRole(name: string) {
    this.http.post(BACKEND_URL + 'role', {name: name})
      .subscribe(
        (role: any) => {
          this.roles.push(role);
          this.rolesChanged.next([...this.roles]);
        }
      );
  }
}
