import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConcursoService, Concurso } from '../concurso/concurso.service';
import { MascotaService, Mascota } from '../mascota/mascota.service';
import { VotoService, Voto, ConcursoVoto, VotoConcurso } from './voto.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';


@Component({
  selector: 'app-voto',
  templateUrl: './voto.component.html'
})
export class VotoComponent implements OnInit {
   form: FormGroup;
  errorMessage: string;
  formSubmitted: boolean;
  imagenSeleccion: string;
  descripcionSeleccion: string;
  nombreSeleccion: string;
  fechaNacSeleccion: string;
  mascotita : Mascota;
  concurso : number;
  concursos: Concurso[];
  voto: Voto;
  votoConcurso: VotoConcurso[];
  botonActivado :Boolean = true;



  constructor(
    private votoService: VotoService,
    private concursoService: ConcursoService,
    private route: ActivatedRoute,
    private router: Router) {
      this.voto= { id : null , concurso : null } ;
}

  ngOnInit() {
this.botonActivado=!this.botonActivado;
      this.concursoService.buscarConcursos()
      .then(concursos => this.concursos = concursos)
      .catch(error => this.errorMessage = <any>error);

      this.votoService.buscarVotoDeUsuario()
      .then(votoConcurso => this.votoConcurso = votoConcurso)
      .catch(error => this.errorMessage = <any>error);

 }


onClick(concurso: number, mascotaSeleccionada: Mascota, descripcionConcurso: string){

  this.mascotita = mascotaSeleccionada;
  this.concurso = concurso;
  this.imagenSeleccion= this.mascotita.imagen;
  this.descripcionSeleccion = descripcionConcurso;
  this.fechaNacSeleccion = this.mascotita.fechaNacimiento;
  this.nombreSeleccion = this.mascotita.nombre;
  this.botonActivado=true;
  this.votoConcurso.forEach(element => {
    if (element.concurso.id === this.concurso){
      this.botonActivado=false;
    }   
  });

}

submit(){
      this.voto.concurso = this.concurso;
      this.votoService.guardarVoto(this.voto)
      .then(voto => this.ngOnInit())
      .catch(error => this.procesarValidacionesRest(error));
  
}

  onDelete() {
   this.votoConcurso.forEach(element => {
  if (element.concurso.id === this.concurso){
    this.cleanRestValidations();
    this.votoService.eliminarVoto(element.id)
      .then(any => this.ngOnInit())
      .catch(error => this.procesarValidacionesRest(error));
}
});

  }


  cleanRestValidations() {
  }

  procesarValidacionesRest(data) {
  }
}

