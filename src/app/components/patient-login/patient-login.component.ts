import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginserviceService } from 'src/app/services/loginservice.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-patient-login',
  templateUrl: './patient-login.component.html',
  styleUrls: ['./patient-login.component.css']
})
export class PatientLoginComponent implements OnInit {

  model={phone_number:"",otp:""};
  otp_sent=false;
  a:string | null | undefined;
  myForm:FormGroup;
  otpForm: FormGroup;
  constructor(private loginservice:LoginserviceService,private router : Router) {
    this.myForm = new FormGroup({
      mobile: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}$')]),
    });

    this.otpForm = new FormGroup({
      otp: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{6}$')]),
    });

  }
  ngOnInit(): void {
    
  }

  sendOTP() {

    if (this.myForm.valid == false){
      alert("Enter the mobile number correctly");
    }

    else{
      this.loginservice.PatientOTP(this.model.phone_number)
      .subscribe({
        next: (response:any) => {
          if (response == 1) {
            alert('OTP sent successfully.');
            // Move it here to avoid the bug
            this.otp_sent = true; 
          } 
          else {
            alert('OTP sending failed!');
          }
        },
      });
      
    }

    
  }

  verifyOTP() {

    if (this.otpForm.valid == false){
      alert("Enter the OTP number correctly");
    }

    else{
      this.loginservice.PatientVerifyOTP(this.model.phone_number,this.model.otp)
      .subscribe((response:  any) => {
        if (response.token == "not") {
          alert('OTP incorrect!');
          this.router.navigate(["/Patient"]);
        } else {
          localStorage.setItem("patient_token",JSON.stringify({patient_details:this.model,token:response.token}));
          this.router.navigate(["/profiles"]);
        }
      });
    }
  }
}


