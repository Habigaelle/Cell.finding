import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { RechercheComponent } from './recherche/recherche.component';
import { ContactComponent } from './contact/contact.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { TissusComponent} from './tissus/tissus.component';
import { TcellulaireComponent } from './tcellulaire/tcellulaire.component';
import { AppareilsComponent } from './appareils/appareils.component';

const appRoutes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'recherche', component: RechercheComponent },
  { path: 'tissus', component: TissusComponent},
  { path: 'contact', component: ContactComponent },
  { path: 'inscription', component: InscriptionComponent},
  { path: 'tcellulaire', component: TcellulaireComponent},
  { path: 'appareils', component: AppareilsComponent},
  { path: 'connexion', component: ConnexionComponent}]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AccueilComponent,
    RechercheComponent,
    ContactComponent,
    ConnexionComponent,
    InscriptionComponent,
    TissusComponent,
    TcellulaireComponent,
    AppareilsComponent,
  
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
