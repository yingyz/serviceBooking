import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { GuestheaderComponent } from './headers/guestheader/guestheader.component';
import { AuthheaderComponent } from './headers/authheader/authheader.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import { RequestsComponent } from './requests/requests.component';
import { RequestListComponent } from './requests/request-list/request-list.component';
import { RequestDetailComponent } from './requests/request-detail/request-detail.component';
import { RequestFormComponent } from './requests/request-form/request-form.component';
import { RequestItemComponent } from './requests/request-list/request-item/request-item.component';
import {AuthInterceptor} from "./auth/auth-interceptor";
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditProfileComponent } from './dashboard/edit-profile/edit-profile.component';
import { CommentsComponent } from './requests/request-detail/comments/comments.component';
import { CommentFormComponent } from './requests/request-detail/comment-form/comment-form.component';


@NgModule({
  declarations: [
    AppComponent,
    GuestheaderComponent,
    AuthheaderComponent,
    LoginComponent,
    RegisterComponent,
    RequestsComponent,
    RequestListComponent,
    RequestDetailComponent,
    RequestFormComponent,
    RequestItemComponent,
    DashboardComponent,
    EditProfileComponent,
    CommentsComponent,
    CommentFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
