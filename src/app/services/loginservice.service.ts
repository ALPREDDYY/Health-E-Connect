import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { response } from 'express';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

const baseUrl = environment.baseUrl;

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {

  constructor(private http:HttpClient) { }
  
  getAdminData(username: string,password: string) {
      return this.http.get(`${baseUrl}/Admin?username=${username}&password=${password}`, {headers:{'ngrok-skip-browser-warning':'google-chrome'}});
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('currentUser');
    if (token)
        return true;
    return false;
  }

}

