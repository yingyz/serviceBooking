import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {ProfileComponent} from "./dashboard/profile/profile.component";
import {EditProfileComponent} from "./dashboard/edit-profile/edit-profile.component";
import {AuthGuard} from "./auth/auth.guard";


const routes: Routes = [
  {path: '', redirectTo: 'dashboard/profile', pathMatch: 'full'},
  {path: 'dashboard/profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'dashboard/editProfile', component: EditProfileComponent, canActivate: [AuthGuard]},
  {path: 'auth/login', component: LoginComponent},
  {path: 'auth/signup', component: SignupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
