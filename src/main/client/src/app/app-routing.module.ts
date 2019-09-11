import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {RequestsComponent} from "./requests/requests.component";
import {RequestFormComponent} from "./requests/request-form/request-form.component";
import {RequestDetailComponent} from "./requests/request-detail/request-detail.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {EditProfileComponent} from "./dashboard/edit-profile/edit-profile.component";

const appRoutes: Routes = [
  { path:'', redirectTo: '/requests', pathMatch: 'full'},
  { path: 'auth/login', component: LoginComponent},
  { path: 'auth/register', component: RegisterComponent},
  { path: 'dashboard', component: DashboardComponent},
  {path: 'editProfile', component: EditProfileComponent},
  { path: 'requests', component: RequestsComponent,
    children: [
      {path: 'new', component: RequestFormComponent},
      {path: ':id', component: RequestDetailComponent},
      {path: ':id/edit', component: RequestFormComponent}
  ]}
]

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
