import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginserviceService } from 'src/app/services/loginservice.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  model={username:"",password:""}
  getData:any
  type="password";
  icon="fa fa-fw fa-eye";
  myForm:FormGroup; 
  constructor(private loginservice:LoginserviceService, private router : Router) { 

    this.myForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')]),
      password: new FormControl('',Validators.required)
    });

  }

  ngOnInit(): void {
  }
  showpass(){
   if (this.type=="password"){
      this.type="text";
      this.icon="fa fa-fw fa-eye-slash"
   }
   else{
    this.type="password";
    this.icon="fa fa-fw fa-eye"
    }
  }

  loginadmin() {
    let user=this.model.username;
    let password=this.model.password;
    if (this.myForm.valid == false){
        alert("Please fill all the fields");
    }
    else{
      this.loginservice.getAdminData(user,password)
        .subscribe((response: any) => {
          console.log(response);
          if (response.token == "not") {
            alert('username and password doesnt match!');
          } else {
            localStorage.setItem("admin_token",JSON.stringify({ admin_email_id:user,token:response.token}));
            this.router.navigate(["/admin-dashboard"]);
          }
        })

    }
  } 
}

