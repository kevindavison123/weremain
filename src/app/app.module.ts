import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from "./app.routing";

import { AppComponent } from './app.component';
import { AboutComponent } from './pages/about/about.component';
import { NewsComponent } from './pages/news/news.component';
import { DonateComponent } from './pages/donate/donate.component';
import { SocialComponent } from './pages/social-media/social.media.component';
import { CausesComponent} from './pages/causes/causes.component';
import { HomeComponent} from './pages/home/home.component';
import { HeaderComponent } from './shared/nav/nav.component';


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    NewsComponent,
    DonateComponent,
    SocialComponent,
    CausesComponent,
    HomeComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
