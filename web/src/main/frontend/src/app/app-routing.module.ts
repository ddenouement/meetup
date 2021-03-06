import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {RegisterSpeakerComponent} from "./register-speaker/register-speaker.component";
import {SpeakerProfileComponent} from "./speaker-profile/speaker-profile.component";
import {VerificationComponent} from "./verification/verification.component";
import {CreateArticleComponent} from "./create-article/create-article.component";
import {FeedbackComponent} from "./feedback/feedback.component";
import {SpeakerProfileToUsersComponent} from "./speaker-profile-to-users/speaker-profile-to-users.component";
import {AdminTableComponent} from "./admin-table/admin-table.component";
import {DictionariesComponent} from "./dictionaries/dictionaries.component";
import {CommentSectionComponent} from "./comment-section/comment-section.component";
import {ArticleViewComponent} from "./article-view/article-view.component";
import {ListenerProfileComponent} from "./listener-profile/listener-profile.component";
import {ListenerProfileToUsersComponent} from "./listener-profile-to-users/listener-profile-to-users.component";
import {ApproveToSpeakerComponent} from "./approve-to-speaker/approve-to-speaker.component";
import {ForgotPasswordComponent} from "./forgot-password/forgot-password.component";
import {MeetupCreateComponent} from "./meetup-create/meetup-create.component";
import {MeetupListComponent} from "./meetup-list/meetup-list.component";
import {MeetupProfileComponent} from "./meetup-profile/meetup-profile.component";
import {SubscribeComponent} from "./subscribe/subscribe.component";
import {ActicleListComponent} from "./article-list/acticle-list.component";
import {AdminBagesComponent} from "./admin-bages/admin-bages.component";
import {NotificationsComponent} from "./notifications/notifications.component";
import {DeactivateComponent} from "./deactivate/deactivate.component";
import {UserComplaintsComponent} from "./user-complaints/user-complaints.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {NotFoundComponent} from "./not-found/not-found.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'register-speaker', component: RegisterSpeakerComponent},
  {path: 'speaker-profile', component: SpeakerProfileComponent},
  {path: 'verify', component: VerificationComponent},
  {path: 'deactivate', component: DeactivateComponent},
  {path: 'create-article', component: CreateArticleComponent},
  {path: 'feedback/:meetupId', component: FeedbackComponent},
  {path: 'admin-table', component: AdminTableComponent},
  {path: 'speaker-profile/:speakerId', component: SpeakerProfileToUsersComponent},
  {path: 'dictionaries', component:DictionariesComponent},
  {path: 'comment-section', component:CommentSectionComponent},
  {path: 'article-view/:articleId', component: ArticleViewComponent},
  {path: 'listener-profile', component: ListenerProfileComponent},
  {path: 'listener-profile/:listenerId', component: ListenerProfileToUsersComponent},
  {path: 'approve-to-speaker', component: ApproveToSpeakerComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'speaker-profile/:id', component: SpeakerProfileToUsersComponent},
  {path: 'meetup-create', component: MeetupCreateComponent},
  {path: 'meetup-edit/:meetupId', component: MeetupCreateComponent},
  {path: 'meetup-list', component: MeetupListComponent},
  {path: 'article-list', component: ActicleListComponent},
  {path: 'meetup-profile/:meetupId', component: MeetupProfileComponent},
  {path: 'user-complaints/:userId', component: UserComplaintsComponent},
  {path: 'subscriptions', component: SubscribeComponent},
  {path: 'admin-badges', component: AdminBagesComponent},
  {path: 'notifications', component: NotificationsComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: 'not-found', component: NotFoundComponent},


  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  // imports: [RouterModule.forRoot(routes)],
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
