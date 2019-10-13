// @ts-ignore
import {BrowserModule} from '@angular/platform-browser';
// @ts-ignore
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
}
// @ts-ignore
  from "@angular/material";

// @ts-ignore
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {RegisterSpeakerComponent} from './register-speaker/register-speaker.component';
// @ts-ignore
import {HttpClientModule} from "@angular/common/http";
// @ts-ignore
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
// @ts-ignore
import {MatOptionModule} from "@angular/material/core";
// @ts-ignore
import {MatSelectModule} from "@angular/material/select";
import { MatPasswordStrengthModule } from '@angular-material-extensions/password-strength';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";


// @ts-ignore
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
    MatPasswordStrengthModule,
    MatSlideToggleModule,
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
