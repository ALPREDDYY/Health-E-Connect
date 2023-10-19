import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatButtonModule} from '@angular/material/button';
import { MatDividerModule} from '@angular/material/divider';

import { NgxAgoraModule, AgoraConfig } from 'ngx-agora';
import { NgChartsModule } from 'ng2-charts';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { UserSelectComponent } from './components/user-select/user-select.component';
import { environment } from 'src/environments/environment';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PatientDashboardComponent } from './components/patient-dashboard/patient-dashboard.component';
import { DoctorDashboardComponent } from './componenets/doctor-dashboard/doctor-dashboard.component';
import { AddDoctorComponent } from './components/add-doctor/add-doctor.component';
import { AddProfileComponent } from './components/add-profile/add-profile.component';
import { UserProfilesComponent } from './components/user-profiles/user-profiles.component';
import { AddAdminComponent } from './components/add-admin/add-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminLoginComponent,    
    UserSelectComponent, PatientDashboardComponent, DoctorDashboardComponent, AddDoctorComponent, AddProfileComponent, UserProfilesComponent, AddAdminComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxAgoraModule.forRoot({ AppID: environment.agora.appId }),
    BrowserAnimationsModule,
    MatIconModule, 
    MatSidenavModule,
    NgChartsModule,
    MatMenuModule,
    MatToolbarModule,
    MatButtonModule,
    MatDividerModule,
    ReactiveFormsModule
  ],
  exports: [MatIconModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
