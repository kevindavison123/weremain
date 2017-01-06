import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import { AppComponent } from './app.component';
import { AboutComponent } from './pages/about/about.component';
import { BlogComponent } from './pages/blog/blog.component';
import { GetInvolvedComponent } from './pages/get-involved/get.involved.component';
import { SocialComponent } from './pages/social-media/social.media.component';
import { StoriesComponent} from './pages/stories/stories.component';
import { IssuesComponent} from './pages/issues/issues.component';
import { HomeComponent} from './pages/home/home.component';

const appRoutes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'blog', component:  BlogComponent},
  {path: 'issues', component: IssuesComponent}


];


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    BlogComponent,
    GetInvolvedComponent,
    SocialComponent,
    StoriesComponent,
    IssuesComponent,
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
