import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { PatientDashboardComponent } from './components/patient-dashboard/patient-dashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ViewDoctorsComponent } from './components/view-doctors/view-doctors.component';
import { ViewAdminsComponent } from './components/view-admins/view-admins.component';
import { AddAdminComponent } from './components/add-admin/add-admin.component';
import { AddDoctorComponent } from './components/add-doctor/add-doctor.component';
import { UserProfilesComponent } from './components/user-profiles/user-profiles.component';
import { AddProfileComponent } from './components/add-profile/add-profile.component';
import { DoctorLoginComponent } from './components/doctor-login/doctor-login.component';
import { PatientLoginComponent } from './components/patient-login/patient-login.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { UserSelectComponent } from './components/user-select/user-select.component';
import { AuthGuard } from './auth.guard';
import { DoctorauthguardGuard } from './authguards/doctorauthguard.guard';
import { AdminauthguardGuard } from './authguards/adminauthguard.guard';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'doctor-dashboard', component: DoctorDashboardComponent,canActivate:[DoctorauthguardGuard]},
  { path: 'patient-dashboard', component: PatientDashboardComponent,canActivate: [AuthGuard]},
  { path: 'admin-dashboard', component: AdminDashboardComponent,canActivate:[AdminauthguardGuard]},
  { path: 'view-doctors', component: ViewDoctorsComponent,canActivate:[AdminauthguardGuard]},
  { path: 'view-admins', component: ViewAdminsComponent,canActivate:[AdminauthguardGuard]},
  { path: 'add-admin', component: AddAdminComponent,canActivate:[AdminauthguardGuard]},
  { path: 'add-doctor', component: AddDoctorComponent,canActivate:[AdminauthguardGuard]},
  { path: 'profiles', component: UserProfilesComponent,canActivate:[AuthGuard]},
  { path: 'add-profile', component: AddProfileComponent, canActivate:[AuthGuard]},
  { path: 'Doctor', component:DoctorLoginComponent},
  { path: 'Patient', component:PatientLoginComponent},
  { path: 'Admin', component:AdminLoginComponent},
  { path: 'home', component: UserSelectComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
