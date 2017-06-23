import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ConcursoService extends RestBaseService {
  private concursoUrl = '/rest/concurso';

  constructor(private http: Http) { super(); }

//Busca concurso de usuario partular
  buscarConcurso(): Promise<Concurso> {
    return this.http.get(ConcursoService.serverUrl + this.concursoUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Concurso;
      })
      .catch(this.handleError);
  }

  //Busca todos los concursos de la base de datos
    buscarConcursos(): Promise<Concurso[]> {
    return this.http.get(ConcursoService.serverUrl + this.concursoUrl + "/all" , this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Concurso[];
      })
      .catch(this.handleError);
  }

  guardarConcurso(value: Concurso): Promise<Concurso> {
    return this.http.post(ConcursoService.serverUrl + this.concursoUrl, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Concurso;
      })
      .catch(this.handleError);
  }
}

export interface Concurso {
  id: number;
  descripcion: string;
  mascota: string;
  cantidadVotos: number;

}
