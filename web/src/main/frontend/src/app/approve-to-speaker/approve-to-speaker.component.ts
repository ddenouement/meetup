import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  Validators
} from "@angular/forms";
import {ErrorStateMatcher} from '@angular/material/core';
import {Router} from "@angular/router";
import {ApproveToSpeakerService} from "./approve-to-speaker.service";
import {LanguagesList} from "../models/languagesList";
import {MustMatch} from "../register-speaker/register-speaker.component";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-approve-to-speaker',
  templateUrl: './approve-to-speaker.component.html',
  styleUrls: ['./approve-to-speaker.component.scss']
})

export class ApproveToSpeakerComponent implements OnInit {


  approveForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  languages: LanguagesList [];
  public loading = false;
  public error: '';
  public firstName: string;
  public lastName: string;
  public login: string;
  public email: string;
  public about: string;


  constructor(
    public approveService: ApproveToSpeakerService,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  get form() {
    return this.approveForm.controls;
  }

  onSubmit() {
    this.approve();
  }

  public approve(): void {
    let langList: number[] = [];
    for (let i in this.approveForm.get('languages').value) {
      langList[i] = this.approveForm.get('languages').value[i].id;
    }
    const user = <User>{
      firstName: this.approveForm.get('firstName').value,
      lastName: this.approveForm.get('lastName').value,
      login: this.approveForm.get('login').value,
      email: this.approveForm.get('email').value,
      about: this.approveForm.get('about').value,
      languageIds: langList
    };
    this.loading = true;
    this.approveForm.controls['firstName'].disable();
    this.approveForm.controls['lastName'].disable();
    this.approveForm.controls['login'].disable();
    this.approveForm.controls['about'].disable();
    this.approveForm.controls['languages'].disable();
    this.approveForm.controls['email'].disable();
    this.httpClient.post("/api/v1/user/register/speaker", user).subscribe(data => {
        this.router.navigate(['/verify']);
      },
      error => {
        this.loading = false;
        this.error = error.error;
        console.log(error);
        this.approveForm.controls['firstName'].enable();
        this.approveForm.controls['lastName'].enable();
        this.approveForm.controls['login'].enable();
        this.approveForm.controls['about'].enable();
        this.approveForm.controls['languages'].enable();
        this.approveForm.controls['email'].enable();
      });
  }

  ngOnInit() {
    this.approveForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      about: [''],
      languages: ['',Validators.required]
    });
    this.approveService.getUser().subscribe(res => {
      this.approveForm = this.formBuilder.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        login: [res['userDTO'].login, Validators.required],
        email: [res['userDTO'].email, [Validators.required, Validators.email]],
        about: [''],
        languages: ['',Validators.required]
      });
    });

    this.approveService.getLanguages().subscribe(res => {
        this.languages = res;
      },
      err => {
        console.log(err);
      });
  }
}
