
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { WelcomeComponent } from './welcome/welcome.component';
import { NuevaMascotaComponent } from './mascota/nueva-mascota.component';
import { MascotaComponent } from './mascota/mascota.component';
import { PerfilComponent } from './perfil/perfil.component';
import { VotoComponent } from './voto/voto.component';
import { ConcursoComponent } from './concurso/concurso.component';
import { RankingComponent } from './voto/ranking.component';
import { RegistrarUsuarioComponent } from './usuario/registrar-usuario.component';

// Route Configuration
export const routes: Routes = [
    { path: '', component: WelcomeComponent },
    { path: 'perfilUsuario', component: PerfilComponent },
    { path: 'voto', component: VotoComponent },
    { path: 'concursoUsuario', component: ConcursoComponent },
    { path: 'registrarUsuario', component: RegistrarUsuarioComponent },
    { path: 'mascotas', component: MascotaComponent },
    { path: 'nuevaMascota/:id', component: NuevaMascotaComponent },
    { path: 'nuevaMascota', component: NuevaMascotaComponent },
    { path: 'ranking', component: RankingComponent },

];

export const routing: ModuleWithProviders = RouterModule.forRoot( routes );