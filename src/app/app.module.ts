import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { APP_BASE_HREF } from '@angular/common';

import { routing } from './app.routes';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { UsuarioService } from './usuario/usuario.service';
import { ProvinciaService } from './provincia/provincia.service';
import { PerfilService } from './perfil/perfil.service';
import { VotoService } from './voto/voto.service';
import { ConcursoService } from './concurso/concurso.service';
import { RankingComponent } from './voto/ranking.component';
import { PerfilComponent } from './perfil/perfil.component';
import { VotoComponent } from './voto/voto.component';
import { ConcursoComponent } from './concurso/concurso.component';
import { MascotaComponent } from './mascota/mascota.component';
import { MenuComponent } from './menu/menu.component';
import { NuevaMascotaComponent } from './mascota/nueva-mascota.component';
import { MascotaService } from './mascota/mascota.service';
import { environment } from '../environments/environment';
import { DatePickerPipe } from './tools/common-pipes.pipe';
import { RegistrarUsuarioComponent } from './usuario/registrar-usuario.component';
import { DatePickerModule } from 'ng2-datepicker';

@NgModule({
  declarations: [
    AppComponent,
    DatePickerPipe,
    WelcomeComponent,
    PerfilComponent,
    VotoComponent,
    ConcursoComponent,
    MascotaComponent,
    MenuComponent,
    RankingComponent,
    NuevaMascotaComponent,
    RegistrarUsuarioComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule,
    HttpModule,
    DatePickerModule,
    routing
  ],
  providers: [MascotaService, UsuarioService, ProvinciaService,
    PerfilService, ConcursoService, VotoService, { provide: APP_BASE_HREF, useValue: environment.baseHref }],
  bootstrap: [AppComponent]
})
export class AppModule { }
