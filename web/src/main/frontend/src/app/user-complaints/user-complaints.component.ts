import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Complaint} from "../models/complaint";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AdminTableService} from "../services/admin-table.service";
import {Registration} from "../models/registration";

@Component({
  selector: 'app-user-complaints',
  templateUrl: './user-complaints.component.html',
  styleUrls: ['./user-complaints.component.scss']
})
export class UserComplaintsComponent implements OnInit {
  private roles: string[];
  private userComplaints: Complaint[];
  private userId: number;
  private currentUser: string;
  private currentUserEmail: string;
  private currentUserId: number;
  private currentUserIsActive: boolean;
  private isLoading = false;
  private deactivateForm: FormGroup;

  constructor(public snackBar:MatSnackBar, public adminService: AdminTableService, private userService: UserService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.deactivateForm = this.formBuilder.group({
      feedback: [''],
    });
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('userId')) {
        this.userId = +paramMap.get('userId');
        this.userService.getComplaintsToUser(this.userId).subscribe(complaints => {
          this.userComplaints = complaints;
          this.userService.getUserById(this.userId).subscribe(res => {
            this.currentUser = res['userDTO'].login;
            this.currentUserId = res['userDTO'].id;
            this.currentUserIsActive = res['userDTO'].active;
            this.currentUserEmail = res['userDTO'].email;
            this.isLoading = false;
          });
        })
      }
    });
  }

  takeUserRole(id: number) {
    this.userService.getUserById(id).subscribe(res => {
      this.roles = res['userDTO'].roles;
      this.roles.includes('SPEAKER') ? this.router.navigate(['/speaker-profile', id]) : this.router.navigate(['/listener-profile', id]);
    })
  }

  onDeactivate(id: number) {
    const user = <Registration>{
      login: this.deactivateForm.get('feedback').value,
      email: this.currentUserEmail
    };
    this.adminService.sendEmail(user).subscribe(res=>{
    });
    this.adminService.deactivateUser(id).subscribe(res => {
      this.ngOnInit();
      let ref = this.snackBar.open('User deactivated', '', {
        duration: 3000
      });

    })
  }

  onActivate(id: number) {
    this.adminService.activateUser(id).subscribe(res => {
      this.ngOnInit();
      let ref = this.snackBar.open('User activated', '', {
        duration: 3000
      });

    });
  }

  goToUser() {
    this.takeUserRole(this.currentUserId);
  }
}
