import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AvailabilityCheck } from '../models/availability-check.model';
import { environment } from 'src/environments/environment';


const baseUrl = environment.baseUrl;

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) { }

  putDoctorStatus(aval:AvailabilityCheck): Observable<number> {
    const token = JSON.parse(localStorage.getItem('doctor_token')!).token;

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': ${token},
      'ngrok-skip-browser-warning':'google-chrome'
    };
 
    return this.http.put<number>(${baseUrl}/DoctorAvailability, aval,{headers});
  }
  getdoctordetails(doctor_email_id : String){
    const token = JSON.parse(localStorage.getItem('doctor_token')!).token;

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': ${token},
      'ngrok-skip-browser-warning':'google-chrome'
    };
    return this.http.get(${baseUrl}/doctordetails?email_id=${doctor_email_id},{headers});
  }

}