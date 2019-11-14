import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegisterService} from "../services/register.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {MatPasswordStrengthComponent} from "@angular-material-extensions/password-strength";
import {ChangePass} from "../models/changePass";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  @ViewChild('passwordComponentWithConfirmation', {static: true})
  passwordComponentWithConfirmation: MatPasswordStrengthComponent;

  private changeForm: FormGroup;
  private loading = false;
  private error: '';
  private showDetails3: boolean;

  constructor(private registerService: RegisterService,
              private httpClient: HttpClient,
              private formBuilder: FormBuilder,
              private router: Router,) { }

  onSubmit() {
    this.changePass();
  }

  public changePass(): void {
    const pass = <ChangePass>{
      currPass: this.changeForm.get('currPass').value,
      password: this.passwordComponentWithConfirmation.password
    };
    this.loading = true;
    this.changeForm.controls['currPass'].disable();
    this.changeForm.controls['password'].disable();
    this.changeForm.controls['confirmPassword'].disable();
    this.registerService.changePassword(pass).subscribe(data => {
        this.router.navigate(['/verify']);
      },
      error => {
        this.error = error.error.error;
        this.loading = false;
        this.changeForm.controls['currPass'].enable();
        this.changeForm.controls['password'].enable();
        this.changeForm.controls['confirmPassword'].enable();
      });
  }

  ngOnInit() {
    this.changeForm = this.formBuilder.group({
      currPass: ['', Validators.required],
      password: ['', [Validators.required]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  onStrengthChanged($event: number) {

  }
}



export function MustMatch(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup) => {
    const control = formGroup.controls[controlName];
    const matchingControl = formGroup.controls[matchingControlName];

    if (matchingControl.errors && !matchingControl.errors.mustMatch) {
      // return if another validator has already found an error on the matchingControl
      return;
    }

    // set error on matchingControl if validation fails
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({mustMatch: true});
    } else {
      matchingControl.setErrors(null);
    }
  }
}
