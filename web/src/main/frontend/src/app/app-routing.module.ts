import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {RegisterSpeakerComponent} from "./register-speaker/register-speaker.component";
import {SpeakerProfileComponent} from "./speaker-profile/speaker-profile.component";
import {VerificationComponent} from "./verification/verification.component";
import {CreateArticleComponent} from "./create-article/create-article.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'register-speaker', component: RegisterSpeakerComponent},
  {path: 'speaker-profile', component: SpeakerProfileComponent},
  {path: 'verify', component: VerificationComponent},
  {path: 'create-article', component: CreateArticleComponent},
  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
