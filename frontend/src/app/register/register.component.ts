import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  registrForm = new FormGroup({
    login: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
  });

  public login: string;
  public email: string;
  public password: string;

  constructor(
    private httpClient: HttpClient
  ) { }

  onSubmit() {
    // TODO: Use EventEmitter with form value
   //console.warn(this.registrForm.value);
   this.register();
  }

  public register(): void {
    const user = <User>{login: this.registrForm.get('login').value, email: this.registrForm.get('email').value, password: this.registrForm.get('password').value};
    this.httpClient.post("/api/v1/user/register/listener", user).subscribe();
  }

  ngOnInit() {
    const pass = document.querySelector('.password');
    const complexityItem = document.getElementsByClassName('complexity__item');
    const complexity = document.querySelector('.complexity');
    const content = document.querySelector('.content');
// On click
    content.addEventListener('click', (event) => {
      // Toggle class "is-active"
      if (event.target === pass) {
        complexity.classList.add('active');
      } else {
        complexity.classList.remove('active');
      }

      pass.addEventListener('input', (eve) => {
        const lengthOfPass = (<HTMLInputElement>eve.target).value.split('').length;
        if (lengthOfPass == 0) {
          complexity.classList.remove('active');
        } else if (lengthOfPass <= 7) {
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.remove('complexity__item--good');
            complexityItem[i].classList.remove('complexity__item--perfect');
          }
          complexityItem[0].classList.add('complexity__item--bad');
        } else if (lengthOfPass <= 8 && (<HTMLInputElement>eve.target).value.match(/[A-Z]/i)) {
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.remove('complexity__item--bad');
            complexityItem[i].classList.remove('complexity__item--perfect');
          }
          for (let i = 0; i < complexityItem.length - 1; i++) {
            complexityItem[i].classList.add('complexity__item--good');
          }
        } else if (lengthOfPass > 8 && (<HTMLInputElement>eve.target).value.match(/[A-Z]/i) && (<HTMLInputElement>eve.target).value.match(/\d/i) && (<HTMLInputElement>eve.target).value.match(/[a-z]/i)) {
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.remove('complexity__item--bad');
            complexityItem[i].classList.remove('complexity__item--good');
          }
          for (let i = 0; i < complexityItem.length; i++) {
            complexityItem[i].classList.add('complexity__item--perfect');
          }
        }
      });
      // Do something else, like open/close menu
    });
  }

}
