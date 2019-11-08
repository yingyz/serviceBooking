import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AdminService} from "../../admin.service";

@Component({
  selector: 'app-roles-list',
  templateUrl: './roles-list.component.html',
  styleUrls: ['./roles-list.component.css']
})
export class RolesListComponent implements OnInit, OnDestroy {

  roles: any[] = [];
  private roleSub: Subscription;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getRolesFromAPI();
    this.roles = this.adminService.getRoles();
    this.roleSub = this.adminService.getRolesStatusListener()
      .subscribe(
        (roles: any[]) => {
          this.roles = roles;
        }
      );
  }

  ngOnDestroy(): void {
    this.roleSub.unsubscribe();
  }
}
