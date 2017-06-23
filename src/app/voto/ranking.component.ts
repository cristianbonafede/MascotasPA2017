import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConcursoService, Concurso } from '../concurso/concurso.service';
import { MascotaService, Mascota } from '../mascota/mascota.service';
import { VotoService, CantidadVotos, ConcursoVoto, VotoConcurso } from './voto.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';


@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html'
})
export class RankingComponent implements OnInit {

  errorMessage: string;
  formSubmitted: boolean;
  votoConcurso: VotoConcurso[];
  concursos: Concurso[];
cantidadVotos: CantidadVotos[];
primerPuesto: number ;
segundoPuesto: number;
tercerPuesto: number ;
mascota : Mascota;
mascota2 : Mascota;
mascota3 : Mascota;



  constructor( 
    private votoService: VotoService,
    private concursoService: ConcursoService,
    private route: ActivatedRoute,
    private router: Router) {
       this.mascota = { id : null, nombre : "", descripcion : "", fechaNacimiento: "" , imagen : "" };
       this.mascota2 = { id : null, nombre : "", descripcion : "", fechaNacimiento: "" , imagen : "" };
       this.mascota3 = { id : null, nombre : "", descripcion : "", fechaNacimiento: "" , imagen : "" };

     }

  ngOnInit() {


      this.concursoService.buscarConcursos()
      .then(concursos => this.concursos = concursos)
      .catch(error => this.errorMessage = <any>error);

      this.votoService.buscarVoto()
      .then(votoConcurso => this.votoConcurso = votoConcurso)
      .catch(error => this.errorMessage = <any>error);


 }




onClick() {

    //obtengo la cantidad de votos de cada concurso
 this.concursos.forEach(element => {
   element.cantidadVotos = 0;
 });
 for (var i = 0; i < this.votoConcurso.length; i++) {
   this.concursos.forEach(element => {
     if(  this.votoConcurso[i].concurso.id === element.id){
       element.cantidadVotos = element.cantidadVotos + 1 ;
     }
   });
 }

 //obtengo el podio
for (var i = 0; i < this.concursos.length; i++) {
if( i === 0 ) {
     this.primerPuesto = this.concursos[i].cantidadVotos ;
     this.segundoPuesto = 0;
     this.tercerPuesto = 0;
}
else{
  if(this.concursos[i].cantidadVotos > this.primerPuesto){
    this.segundoPuesto = this.primerPuesto;
     this.primerPuesto = this.concursos[i].cantidadVotos;
  }
 else{ 
   if(this.concursos[i].cantidadVotos > this.segundoPuesto){
     this.tercerPuesto = this.segundoPuesto;
     this.segundoPuesto = this.concursos[i].cantidadVotos;
   }
   else{
     if(this.concursos[i].cantidadVotos > this.tercerPuesto){
       this.tercerPuesto = this.concursos[i].cantidadVotos;
     }
   }
 }
}
}
//busco mascotas del podio
this.concursos.forEach(element => {
     if(element.cantidadVotos === this.primerPuesto){
         this.votoConcurso.forEach(element2 => {
             if(element.id === element2.concurso.id){
                this.mascota.nombre = element2.concurso.mascota.nombre;
                 this.mascota.descripcion = element2.concurso.mascota.descripcion;
                 this.mascota.imagen = element2.concurso.mascota.imagen;
                 this.mascota.id = element2.concurso.mascota.id;
                 this.mascota.fechaNacimiento = element2.concurso.mascota.fechaNacimiento; 
                 }
         });
  }
  else {
      if(element.cantidadVotos === this.segundoPuesto){
         this.votoConcurso.forEach(element2 => {
            if(element.id === element2.concurso.id){
              this.mascota2.nombre = element2.concurso.mascota.nombre;
              this.mascota2.descripcion = element2.concurso.mascota.descripcion;
              this.mascota2.imagen = element2.concurso.mascota.imagen;
              this.mascota2.id = element2.concurso.mascota.id;
              this.mascota2.fechaNacimiento = element2.concurso.mascota.fechaNacimiento; 
            }
         });
    }
    else{
         if(element.cantidadVotos === this.tercerPuesto){
          this.votoConcurso.forEach(element2 => {
             if(element.id === element2.concurso.id){
               this.mascota3.nombre = element2.concurso.mascota.nombre;
                this.mascota3.descripcion = element2.concurso.mascota.descripcion;
               this.mascota3.imagen = element2.concurso.mascota.imagen;
                this.mascota3.id = element2.concurso.mascota.id;
                 this.mascota3.fechaNacimiento = element2.concurso.mascota.fechaNacimiento; 
             }
          });
       }
   }

  }


});





  }


  cleanRestValidations() {
  }

  procesarValidacionesRest(data) {
  }
}

