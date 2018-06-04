import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { EncyclopedieComponent } from './encyclopedie/encyclopedie.component';
import { RechercheComponent } from './recherche/recherche.component';
import { AccueilComponent } from './accueil/accueil.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { AppareilService } from './_services/appareil.service';
import { AppareilComponent } from './appareil/appareil.component';
import { TissuService } from './_services/tissu.service';
import { TissuComponent } from './tissu/tissu.component';
import { ElementComponent } from './element/element.component';
import { HttpClientModule } from '@angular/common/http';




const appRoutes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'recherche', component: RechercheComponent },
  { path: 'encyclopedie', component: EncyclopedieComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'inscription', component: InscriptionComponent},
  { path: 'connexion', component: ConnexionComponent}]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    ContactComponent,
    EncyclopedieComponent,
    RechercheComponent,
    AccueilComponent,
    InscriptionComponent,
    ConnexionComponent,
    AppareilComponent,
    TissuComponent,
    ElementComponent,
    


  ],
  imports:[RouterModule.forRoot(appRoutes),
      BrowserModule, FormsModule, HttpClientModule,
  ],
  providers: [AppareilService, TissuService],
  bootstrap: [AppComponent]
})
export class AppModule { }
