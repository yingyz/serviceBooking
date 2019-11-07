import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "./auth/auth-interceptor";
import { ProfileComponent } from './dashboard/profile/profile.component';
import { EditProfileComponent } from './dashboard/edit-profile/edit-profile.component';
import { RequestsComponent } from './requests/requests.component';
import { RequestListComponent } from './requests/request-list/request-list.component';
import { RequestDetailComponent } from './requests/request-detail/request-detail.component';
import { RequestFormComponent } from './requests/request-form/request-form.component';
import { RequestItemComponent } from './requests/request-list/request-item/request-item.component';
import { ProvidesComponent } from './provides/provides.component';
import { ProvideDetailComponent } from './provides/provide-detail/provide-detail.component';
import { ProvideListComponent } from './provides/provide-list/provide-list.component';
import { ProvideItemComponent } from './provides/provide-list/provide-item/provide-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    SignupComponent,
    ProfileComponent,
    EditProfileComponent,
    RequestsComponent,
    RequestListComponent,
    RequestDetailComponent,
    RequestFormComponent,
    RequestItemComponent,
    ProvidesComponent,
    ProvideDetailComponent,
    ProvideListComponent,
    ProvideItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
