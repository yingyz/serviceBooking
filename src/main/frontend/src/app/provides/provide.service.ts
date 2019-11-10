import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {ServiceProvideModel} from "../models/serviceProvide.model";
import {Subject} from "rxjs";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";

const BACKEND_URL = environment.apiUrl + '/provider/';

@Injectable({providedIn: 'root'})
export class ProvideService {
  private provides: ServiceProvideModel[] = [];
  private providesChanged = new Subject<ServiceProvideModel[]>();
  private myProvide: ServiceProvideModel;
  private myProvideChanged = new Subject<ServiceProvideModel>();

  constructor(private http: HttpClient, private router: Router){}

  getProvides() {
    return this.provides;
  }

  getProvidesChanged() {
    return this.providesChanged.asObservable();
  }

  getProvideByIdx(index: number) {
    return this.provides[index];
  }

  clearMyProvide() {
    this.myProvide = null;
    this.myProvideChanged.next(null);
  }

  getProvideStatusListener() {
    return this.myProvideChanged.asObservable();
  }

  getMyProvide() {
    return this.myProvide;
  }

  getProvidesFromAPI(provideName: string) {
    let URL = BACKEND_URL;
    if (provideName != 'All') {
      URL += provideName;
    }
    this.http.get(URL)
      .pipe(
        map(
          (provides: any[]) => {
            return provides.map(
              provide => {
                return new ServiceProvideModel(
                  provide.serviceId,
                  provide.detail,
                  provide.price,
                  provide.servicetype,
                  provide.userDto
                );
              }
            );
          }
        )
      )
      .subscribe(
        transformedProvides => {
          this.provides = transformedProvides;
          this.providesChanged.next([...this.provides]);
        }
      );
  }

  getMyProvideFromAPI() {
    let URL = BACKEND_URL + 'me';

    return this.http.get(URL)
      .pipe(
        map(
          (provide: any) => {
            if (provide == null) {
              return null;
            }
            else {
              return new ServiceProvideModel(
                provide.serviceId,
                provide.detail,
                provide.price,
                provide.servicetype,
                provide.userDto
              );
            }
          }
        )
      )
      .subscribe(
        transformedProvide => {
          this.myProvide = transformedProvide;
          this.myProvideChanged.next(this.myProvide);
        }
      );
  }

  updateService(detail: string, price: string, servicename: string) {
    let provideData = {detail: detail, price: price, servicename: servicename};
    let URL = BACKEND_URL + 'update';
    this.http.post(URL, provideData)
      .subscribe(
        (provide: any) => {
          let newProvide = new ServiceProvideModel(
            provide.serviceId,
            provide.detail,
            provide.price,
            provide.servicetype,
            provide.userDto
          );
          this.myProvide = newProvide;
          this.myProvideChanged.next(this.myProvide);
          this.router.navigateByUrl('/dashboard/profile');
        },
        error => {
          console.log(error);
        }
      );
  }
}
