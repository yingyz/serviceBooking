import {Injectable} from "@angular/core";
import {RequestModel} from "./request.model";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import {UserModel} from "../auth/user.model";

const BACKEND_URL = environment.apiUrl + '/request/';

@Injectable({providedIn: 'root'})
export class RequestsService {

  private requests = [];

  private requestsChanged = new Subject<RequestModel[]>();

  constructor(private http: HttpClient, private router: Router){}


  getRequests(userRole: string) {
    let URL = BACKEND_URL;
    if (userRole === 'Customer') {
      URL += 'me';
    } else if (userRole === 'Service') {
      URL += 'all';
    }
    this.http.get(
      URL
    )
      .pipe(
        map((requests : any[]) => {
            return requests.map(request => {
              return new RequestModel(
                request.id,
                request.title,
                request.info,
                request.active,
                request.create_At,
                new UserModel(
                  request.user.id,
                  request.user.username,
                  request.user.userInfo.firstname,
                  request.user.userInfo.lastname,
                  request.user.userInfo.streetname,
                  request.user.userInfo.city,
                  request.user.userInfo.state,
                  request.user.userInfo.zipcode,
                  request.user.userInfo.phone,
                  request.user.fullName,
                  request.user.role.name
                )
              );
            });
        })
      )
      .subscribe(
        transformedRequests => {
          this.requests = transformedRequests;
          this.requestsChanged.next([...this.requests]);
        }
      );
  }

  getRequestUpdateListener() {return this.requestsChanged.asObservable();}

  getRequest(id: number) {
    return this.requests[id];
  }

  addRequest(title: string, info: string) {
    let requestData = {title: title, info: info};
    this.http.post(BACKEND_URL, requestData)
      .subscribe(
        (request : any) => {
          let newRequest = new RequestModel(
            request.id,
            request.title,
            request.info,
            request.active,
            request.create_At,
            new UserModel(
              request.user.id,
              request.user.username,
              request.user.userInfo.firstname,
              request.user.userInfo.lastname,
              request.user.userInfo.streetname,
              request.user.userInfo.city,
              request.user.userInfo.state,
              request.user.userInfo.zipcode,
              request.user.userInfo.phone,
              request.user.fullName,
              request.user.role.name
            )
          );
          this.requests.push(newRequest);
          this.requestsChanged.next([...this.requests]);

          this.router.navigate(['/']);
        }
      );
  }

  updateRequest(id: number, localId: number) {

    this.http.put(BACKEND_URL + 'id/' + id, {})
      .subscribe((request : any) => {
        this.requests[localId].active = true;
        this.requestsChanged.next([...this.requests]);

        this.router.navigate(['/']);
      });
  }
}
