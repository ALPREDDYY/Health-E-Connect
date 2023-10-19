import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { UserSelectComponent } from './components/user-select/user-select.component';
import { PatientDashboardComponent } from './components/patient-dashboard/patient-dashboard.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'admin', component:AdminLoginComponent},
  { path: 'home', component: UserSelectComponent},
  { path: 'patient-dashboard', component: PatientDashboardComponent},
  { path: 'doctor-dashboard',component:DoctorDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
