import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import { AppComponent } from './app.component';
import { AboutComponent } from './pages/about/about.component';
import { NewsComponent } from './pages/news/news.component';
import { DonateComponent } from './pages/donate/donate.component';
import { SocialComponent } from './pages/social-media/social.media.component';
import { CausesComponent} from './pages/causes/causes.component';
import { HomeComponent} from './pages/home/home.component';

const appRoutes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'news', component:  NewsComponent},
  {path: 'causes', component: CausesComponent}


];


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    NewsComponent,
    DonateComponent,
    SocialComponent,
    CausesComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
