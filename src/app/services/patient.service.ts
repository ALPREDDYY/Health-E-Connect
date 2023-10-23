import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


const baseUrl = environment.baseUrl;

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  getUserProfileslst(phone_number: string){
    const token = JSON.parse(localStorage.getItem('patient_token')!).token;

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `${token}`,
      'ngrok-skip-browser-warning':'google-chrome'
    };
 
    console.log("scscscs");
    return this.http.get(`${baseUrl}/profiles/`+phone_number,{headers});
    // return "Hello";
  }
  getuserprofilepass(patient_id : number): Observable<any>{
    const token = JSON.parse(localStorage.getItem('patient_token')!).token;

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `${token}`,
      'ngrok-skip-browser-warning':'google-chrome'
    };
    return this.http.get(`${baseUrl}/profiles/pin?patient_id=${patient_id}`,{headers});

  }

}