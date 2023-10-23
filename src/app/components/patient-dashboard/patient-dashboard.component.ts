import { Component, OnInit } from '@angular/core';
import { PatientService } from 'src/app/services/patient.service';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { delay, filter } from 'rxjs/operators';
import { NavigationEnd, Router } from '@angular/router';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { LoginserviceService } from 'src/app/services/loginservice.service';

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css']
})
export class PatientDashboardComponent implements OnInit {
  previous_appointments:boolean;
  patient_id: number = -1;
  patient_details:any;

  sidenav!: MatSidenav;

  constructor(private router: Router,private patientservice:PatientService, private observer: BreakpointObserver, private loginservice: LoginserviceService) { 
    this.previous_appointments=true;
    this.getPatientDetails();
  }

  ngOnInit(): void {
   
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




  w3_close() {
    var box = document.getElementsByClassName("w3-sidenav") as unknown as HTMLCollectionOf<HTMLElement>;
    box[0].style.display="None"
   
  }
  w3_open() {
    var box = document.getElementsByClassName("w3-sidenav") as unknown as HTMLCollectionOf<HTMLElement>;
    box[0].style.display="block"
  }


 showpreviousappointments(){
  this.previous_appointments=!this.previous_appointments;
 }

 getPatientDetails(){
  console.log(localStorage.getItem("patient_details"));
  this.patient_details = JSON.parse( localStorage.getItem("patient_details")!);
  console.log("Sfdgsfh");
  console.log(this.patient_details);
  
 }


  logout() {
    this.loginservice.patient_logout();
    this.router.navigate(['/Patient']);
  }

}
