import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Authentificationrequest} from "../models/authentificationrequest";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private loginForm: FormGroup;
  private error = '';
  private loading = false;

  constructor(
    private loginService: LoginService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  onSubmit() {
    this.do_login();
  }

  public do_login(): void {
    const user = <Authentificationrequest>{
      login: this.loginForm.get('login').value,
      password: this.loginForm.get('password').value
    };
    this.loading = true;
    this.loginForm.controls['login'].disable();
    this.loginForm.controls['password'].disable();
    this.loginService.login(user)
      .subscribe(data => {
          this.loginService.getUser().subscribe(res => {
            if (res['userDTO'].active == false) {
              this.loginService.logoutUser();
              this.router.navigate(['/deactivate']);
            } else {
              this.loginService._logInUser = true;
              if (data['role'] === "SPEAKER") {
                this.router.navigate(['/speaker-profile']);
              } else if (data['role'] === "ADMIN") {
                this.router.navigate(['/admin-table']);
              } else {
                this.router.navigate(['/listener-profile']);
              }
            }
          });
        },
        error => {
          this.error = error.error;
          this.loading = false;
          this.loginForm.controls['login'].enable();
          this.loginForm.controls['password'].enable();
        });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}

