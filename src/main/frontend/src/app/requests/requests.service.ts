import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {RequestModel} from "../models/request.model";
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {map} from "rxjs/operators";

const BACKEND_URL = environment.apiUrl + '/request/';

@Injectable({providedIn: 'root'})
export class RequestsService {
  private requests: RequestModel[] = [];
  private reqestsChanged = new Subject<RequestModel[]>();

  constructor(private authService: AuthService, private http: HttpClient, private router: Router){}

  getRequests() {
    return this.requests;
  }

  getRequestsChanged() {
    return this.reqestsChanged.asObservable();
  }

  getRequestyIdx(idx: number) {
    return this.requests[idx];
  }

  getRequestsFromAPI(provideName: string, languageName: string, page: number = 0) {
    let limit = 2;
    let URL = BACKEND_URL;
    let userRole = this.authService.getUser().role;
    if (userRole === 'Customer') {
      URL += 'me';
    } else if (userRole === 'Service') {
      if (provideName != 'All' && languageName != 'All') {
        URL += provideName + '/' + languageName;
      } else if (provideName != 'All') {
        URL += 'name' + '/' + provideName;
      } else if (languageName != 'All') {
        URL += 'language' + '/' + languageName;
      } else {
        URL += 'All';
      }
      URL += '?page='+page+'&limit='+limit;
    }

    this.http.get(URL)
      .pipe(
        map( (requests: any[]) => {
            return requests.map(
              request => {
                return new RequestModel(
                  request.requestId,
                  request.servicetype,
                  request.info,
                  request.active,
                  request.userDto,
                  request.create_At,
                  request.update_At
                );
              }
            );
          }
        )
      )
      .subscribe(
        transformedRequests => {
          this.requests = transformedRequests;
          this.reqestsChanged.next([...this.requests]);
        }
      );
  }

  addRequest(servicetype: string, info: string) {
    let requestData = {servicetype: servicetype, info: info};
    this.http.post(BACKEND_URL, requestData)
      .subscribe(
        (request: any) => {
          let newRequest = new RequestModel(
            request.requestId,
            request.title,
            request.info,
            request.active,
            request.userDto,
            request.create_At,
            request.update_At
          );
          this.requests.push(newRequest);
          this.reqestsChanged.next([...this.requests]);
          this.router.navigateByUrl('/requests');
        }
      );
  }

  updateRequest(servicetype: string, info: string, index: number) {
    let currentRequest: RequestModel = this.requests[index];
    let requestData = {servicetype: servicetype, info: info};
    this.http.put(BACKEND_URL + 'id/' + currentRequest.requestId, requestData)
      .subscribe(
        (request: any) => {
          let newRequest = new RequestModel(
            request.requestId,
            request.title,
            request.info,
            request.active,
            request.userDto,
            request.create_At,
            request.update_At
          );
          this.requests[index] = newRequest;
          this.reqestsChanged.next([...this.requests]);
          this.router.navigateByUrl('/requests');
        }
      );
  }
}
