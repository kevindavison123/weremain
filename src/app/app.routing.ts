import { Routes, RouterModule } from "@angular/router";
import { AboutComponent } from './pages/about/about.component';
import { NewsComponent } from './pages/news/news.component';
import { DonateComponent } from './pages/donate/donate.component';
import { SocialComponent } from './pages/social-media/social.media.component';
import { CausesComponent} from './pages/causes/causes.component';
import { HomeComponent} from './pages/home/home.component';

const APP_ROUTES: Routes = [
	{ path: '', redirectTo: '/home', pathMatch: 'full' },
	{ path: 'home', 	component: HomeComponent },
	{ path: 'causes', 	component: CausesComponent },
	{ path: 'social', 	component: SocialComponent },
	{ path: 'donate',	component: DonateComponent },
	{ path: 'news', 	component: NewsComponent },
	{ path: 'about', 	component: AboutComponent }
];

export const routing = RouterModule.forRoot(APP_ROUTES);