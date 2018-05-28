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

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'recherche', component: RechercheComponent },
  { path: 'encyclopedie', component: EncyclopedieComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'inscription', component: InscriptionComponent}]

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
    InscriptionComponent
  ],
  imports:[RouterModule.forRoot(appRoutes),
      BrowserModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
