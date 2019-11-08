import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AdminService} from "../../admin.service";

@Component({
  selector: 'app-servicelist',
  templateUrl: './servicelist.component.html',
  styleUrls: ['./servicelist.component.css']
})
export class ServicelistComponent implements OnInit, OnDestroy {

  serviceTypes: any[] = [];
  private serviceSub: Subscription;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getServiceTypesFromAPI();
    this.serviceTypes = this.adminService.getServiceTypes();
    this.serviceSub = this.adminService.getServiceStatusListener()
      .subscribe(
        (serviceTypes: any[]) => {
          this.serviceTypes = serviceTypes;
        }
      );
  }

  ngOnDestroy(): void {
    this.serviceSub.unsubscribe();
  }
}
