import { Component, OnInit } from '@angular/core';
import { AvailabilityCheck } from 'src/app/models/availability-check.model';

import { PatientService } from 'src/app/services/patient.service';

import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { delay, filter } from 'rxjs/operators';
import { NavigationEnd, Router } from '@angular/router';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LoginserviceService } from 'src/app/services/loginservice.service';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {
  available: boolean;
  showpreviousappointments=true;
  doctor_details:any;
  channel_name:string = '';
  sidenav!: MatSidenav;
  

  constructor(private router: Router,private patientservice:PatientService, private observer: BreakpointObserver, private loginservice: LoginserviceService) { 
    this.available = true;
    this.getDoctorDetails();
    this.doctor_details = JSON.parse(localStorage.getItem("doctor_details")!);
  }

  ngOnInit(): void {
    this.toggleChange();
  }



  ngAfterViewInit() {
    this.observer
      .observe(['(max-width: 800px)'])
      .pipe(delay(1), untilDestroyed(this))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });

    this.router.events
      .pipe(
        untilDestroyed(this),
        filter((e) => e instanceof NavigationEnd)
      )
      .subscribe(() => {
        if (this.sidenav.mode === 'over') {
          this.sidenav.close();
        }
      });
  }
  getDoctorDetails(){
    const username=JSON.parse(localStorage.getItem("doctor_token")!).doctor_email_id;
    
    ({
      next:(res:any)=>{
        this.doctor_details=res;
        localStorage.setItem("doctor_details",JSON.stringify(this.doctor_details));
        console.log(this.doctor_details);
      }
    });
  }
  
  doctorPreviousAppointments(){
    this.router.navigate(['doctor-prev-appointments']);
  }

  toggleChange() {
    if(this.available==true){
      this.available=false;
    }
    else if(this.available==false){
      this.available=true;
    }

    let aval = new AvailabilityCheck();
    aval.doctorId = this.doctor_details.doctorId;
    aval.status = this.available;
    

   
  }

  

  
  w3_close() {
    var box = document.getElementsByClassName("w3-sidenav") as unknown as HTMLCollectionOf<HTMLElement>;
    box[0].style.display="None"
   
  }
  w3_open() {
    var box = document.getElementsByClassName("w3-sidenav") as unknown as HTMLCollectionOf<HTMLElement>;
    box[0].style.display="block"
  }
  showappointments(){
    this.showpreviousappointments=!this.showpreviousappointments;
  }

  logout() {
    // console.log('component');
    
    this.router.navigate(['/Doctor']);
  }
}