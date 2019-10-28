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
import { MatPasswordStrengthModule } from '@angular-material-extensions/password-strength';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import { SpeakerProfileComponent } from './speaker-profile/speaker-profile.component';
import { VerificationComponent } from './verification/verification.component';
import {RatingModule} from "ng-starrating";
import { CreateArticleComponent } from './create-article/create-article.component';
import { ListenerProfileComponent } from './listener-profile/listener-profile.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { AdminComponent } from './admin/admin.component';
import { SpeakerProfileToUsersComponent } from './speaker-profile-to-users/speaker-profile-to-users.component';
import { SidebarComponent } from './sidebar/sidebar.component';/*
import { DictionariesComponent } from './dictionaries/dictionaries.component';
import { LanguagesCrudComponent } from './languages-crud/languages-crud.component';
import { TopicsCrudComponent } from './topics-crud/topics-crud.component';*/
import {MatTabsModule} from "@angular/material/tabs";

@NgModule({
  declarations: [
    AppComponent,
    ToolbarMenuComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    RegisterSpeakerComponent,
    SpeakerProfileComponent,
    VerificationComponent,
    CreateArticleComponent,
    ListenerProfileComponent,
    FeedbackComponent,
    AdminComponent,
    SpeakerProfileToUsersComponent,
    SidebarComponent

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
    RatingModule,
    MatTabsModule,
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
