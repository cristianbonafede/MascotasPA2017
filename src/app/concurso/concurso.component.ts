import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MascotaService, Mascota } from '../mascota/mascota.service';
import { ConcursoService, Concurso } from './concurso.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';


@Component({
  selector: 'app-concurso',
  templateUrl: './concurso.component.html'
})
export class ConcursoComponent implements OnInit {
  form: FormGroup;
  errorMessage: string;
  formSubmitted: boolean;
  mascotas: Mascota[];



  constructor(fb: FormBuilder,
    private concursoService: ConcursoService,
    private mascotaService: MascotaService,
    private route: ActivatedRoute,
    private router: Router) {
    this.form = fb.group({
      'id': [null, null],
      'mascota': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'descripcion': [null, [Validators.required, Validators.minLength(3), Validators.maxLength(250)]],
    });
    this.form.patchValue({ id: null, descripcion: ''});
  }

  ngOnInit() {

      this.mascotaService.buscarMascotas()
      .then(mascotas => this.mascotas = mascotas)
      .catch(error => this.errorMessage = <any>error);


    this.concursoService.buscarConcurso()
      .then(Concurso => this.form.patchValue(Concurso))
      .catch(error => this.errorMessage = <any>error);


  }

  submitForm() {
    this.cleanRestValidations();
    if (this.form.valid) {
      this.concursoService.guardarConcurso(this.form.value)
        .then(usuario => this.router.navigate(['/']))
        .catch(error => this.errorMessage = <any>error);
    } else {
      this.formSubmitted = true;
    }
  }


  cleanRestValidations() {
  }

  procesarValidacionesRest(data) {

  }
}

