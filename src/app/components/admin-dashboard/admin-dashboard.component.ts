import { Component } from '@angular/core';

import { BreakpointObserver } from '@angular/cdk/layout';
import { MatSidenav } from '@angular/material/sidenav';
import { delay, filter } from 'rxjs/operators';
import { NavigationEnd, Router } from '@angular/router';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { AdminService } from 'src/app/services/admin.service';
import { LoginserviceService } from 'src/app/services/loginservice.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
    sidenav!: MatSidenav;

    admin_details: any;


    constructor(private router: Router, private observer: BreakpointObserver, private admin_service: AdminService, private loginservice: LoginserviceService) {
      
    }
    w3_close() {
        var box = document.getElementsByClassName("w3-sidenav") as unknown as HTMLCollectionOf<HTMLElement>;
        box[0].style.display="None"
    
    }
    w3_open() {
        var box = document.getElementsByClassName("w3-sidenav") as unknown as HTMLCollectionOf<HTMLElement>;
        box[0].style.display="block"
    }

    ngOnInit(): void {
        this.getAdminDetails();
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

  getAdminDetails(){
    const username=JSON.parse(localStorage.getItem("admin_token")!).admin_email_id;
    this.admin_service.getAdminDetails(username)
    .subscribe({
      next:(res:any)=>{
        this.admin_details=res;
        localStorage.setItem("admin_details",JSON.stringify(this.admin_details));
        console.log(this.admin_details);
      }
    });
  }

  logout() {
    this.loginservice.admin_logout();
    this.router.navigate(['/Admin']);
  }

}
