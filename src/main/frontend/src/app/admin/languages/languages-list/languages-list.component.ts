import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AdminService} from "../../admin.service";

@Component({
  selector: 'app-languages-list',
  templateUrl: './languages-list.component.html',
  styleUrls: ['./languages-list.component.css']
})
export class LanguagesListComponent implements OnInit, OnDestroy {

  languages: any[] = [];
  private languageSub: Subscription;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getLanguagesFromAPI();
    this.languages = this.adminService.getLanguages();
    this.languageSub = this.adminService.getLanguageStatusListener()
      .subscribe(
        (languages: any[]) => {
          this.languages = languages;
        }
      );
  }

  ngOnDestroy(): void {
    this.languageSub.unsubscribe();
  }
}
