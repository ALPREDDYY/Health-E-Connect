import { Component } from '@angular/core';

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css']
})
export class PatientDashboardComponent {
  previous_appointments=true
  patient_id: number = -1;
  patient_details:any;
   }
   constructor(private patientservice:PatientService)
   {
    this.getPatientDetails();
   }
 
   ngOnInit(): void {
    
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
}
 
