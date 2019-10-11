import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';


import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ToolbarMenuComponent} from './toolbar-menu/toolbar-menu.component';
import {
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatInputModule,
  MatMenuModule,
  MatToolbarModule
} from "@angular/material";

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {RegisterSpeakerComponent} from './register-speaker/register-speaker.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";


@NgModule({
  declarations: [
    AppComponent,
    ToolbarMenuComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    RegisterSpeakerComponent
  ],
  imports: [
    BrowserModule,
    MatInputModule,
    AppRoutingModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatMenuModule,
    MatGridListModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatOptionModule,
    MatSelectModule,
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
