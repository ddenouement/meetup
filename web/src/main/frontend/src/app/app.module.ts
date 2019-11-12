import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ToolbarMenuComponent} from './toolbar-menu/toolbar-menu.component';

import {
  MatButtonModule,
  MatCardModule, MatChipsModule,
  MatGridListModule,
  MatInputModule,
  MatMenuModule,
  MatBadgeModule,
  MatToolbarModule,
  MatPaginatorModule,
  MatProgressSpinnerModule, MatNativeDateModule
} from "@angular/material";
import {
  TimepickerModule,
  BsDatepickerModule,
  TimepickerConfig,
  BsDatepickerConfig
} from 'ngx-bootstrap';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {RegisterSpeakerComponent} from './register-speaker/register-speaker.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatPasswordStrengthModule} from '@angular-material-extensions/password-strength';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {SpeakerProfileComponent} from './speaker-profile/speaker-profile.component';
import {VerificationComponent} from './verification/verification.component';
import {RatingModule} from "ng-starrating";
import {CreateArticleComponent} from './create-article/create-article.component';
import {ListenerProfileComponent} from './listener-profile/listener-profile.component';
import {FeedbackComponent} from './feedback/feedback.component';
import {SpeakerProfileToUsersComponent} from './speaker-profile-to-users/speaker-profile-to-users.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {MatTableModule} from "@angular/material/table";
import {LanguagesCrudComponent} from './languages-crud/languages-crud.component';
import {TopicsCrudComponent} from './topics-crud/topics-crud.component';
import {IconsModule} from "./icons/icons.module";
import {CommentSectionComponent} from "./comment-section/comment-section.component";
import {ComplaintComponent} from "./complaint/complaint.component";
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {DictionariesComponent} from "./dictionaries/dictionaries.component";
import {PickerModule} from '@ctrl/ngx-emoji-mart';
import {MatTabsModule} from "@angular/material/tabs";
import {ArticleViewComponent} from './article-view/article-view.component';
import {AdminTableComponent} from './admin-table/admin-table.component';
import {ApproveToSpeakerComponent} from './approve-to-speaker/approve-to-speaker.component';
import {ListenerProfileToUsersComponent} from './listener-profile-to-users/listener-profile-to-users.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {MeetupCreateComponent} from "./meetup-create/meetup-create.component";
import {MeetupListComponent} from "./meetup-list/meetup-list.component";
import {MeetupProfileComponent} from "./meetup-profile/meetup-profile.component";
import {FromNowPipe} from "./pipes/from_now_pipe";
import {MatIconModule} from "@angular/material/icon";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {FooterComponent} from "./footer/footer.component";
import {ActicleListComponent} from './article-list/acticle-list.component';
import {SubscribeComponent} from './subscribe/subscribe.component';
import {AdminBagesComponent} from './admin-bages/admin-bages.component';
import {NotificationsComponent} from './notifications/notifications.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {OwlDateTimeModule, OwlNativeDateTimeModule} from "ng-pick-datetime";
import {TimepickerActions} from "ngx-bootstrap/timepicker";
import {CommonModule} from "@angular/common";
import {MatSnackBarModule} from '@angular/material'
import {MAT_SNACK_BAR_DEFAULT_OPTIONS} from "@angular/material";
import {FiltersPanelComponent} from './filters-panel/filters-panel.component';
import {HttpCustom} from "./services/HttpCustom";
import {ResponseInterceptor} from "./services/ResponseInterseptor";
import {ForbiddenComponent} from './forbidden/forbidden.component';
import {NotFoundComponent} from './not-found/not-found.component';

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
    SpeakerProfileToUsersComponent,
    SidebarComponent,
    AdminTableComponent,
    ApproveToSpeakerComponent,
    ListenerProfileToUsersComponent,
    ForgotPasswordComponent,
    ArticleViewComponent,
    AdminTableComponent,
    DictionariesComponent,
    LanguagesCrudComponent,
    TopicsCrudComponent,
    ComplaintComponent,
    CommentSectionComponent,
    MeetupCreateComponent,
    MeetupListComponent,
    MeetupProfileComponent,
    FooterComponent,
    FromNowPipe,
    SubscribeComponent,
    AdminBagesComponent,
    SubscribeComponent,
    FooterComponent,
    ActicleListComponent,
    NotificationsComponent,
    FiltersPanelComponent,
    ForbiddenComponent,
    NotFoundComponent
  ],
  imports: [
    IconsModule,
    PickerModule,
    BsDatepickerModule.forRoot(),
    MatTabsModule,
    MatProgressSpinnerModule,
    MatNativeDateModule,
    OwlNativeDateTimeModule,
    MatExpansionModule,
    CommonModule,
    MatListModule,
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
    MatChipsModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatBadgeModule,
    NgbModule,
    MatDatepickerModule,
    OwlDateTimeModule,
    TimepickerModule,
    MatSnackBarModule,
  ],
  exports: [MatBadgeModule],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [TimepickerConfig, TimepickerActions, BsDatepickerConfig, {
    provide: MAT_SNACK_BAR_DEFAULT_OPTIONS,
    useValue: {duration: 2500}
  }
    ,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpCustom,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ResponseInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
