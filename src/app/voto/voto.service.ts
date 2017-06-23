import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { MascotaService, Mascota } from '../mascota/mascota.service'
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class VotoService extends RestBaseService {
  private votoUrl = '/rest/voto';
  voto: Voto;
  concursoVoto: ConcursoVoto;
  votoConcurso: VotoConcurso;

  constructor(private http: Http) { super(); }

  buscarVoto(): Promise<VotoConcurso[]> {
    return this.http.get(VotoService.serverUrl + this.votoUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as VotoConcurso[];
      })
      .catch(this.handleError);
  }


    buscarVotoDeUsuario(): Promise<VotoConcurso[]> {
    return this.http.get(VotoService.serverUrl + this.votoUrl + "/voto", this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as VotoConcurso[];
      })
      .catch(this.handleError);
  }
  guardarVoto(value: Voto): Promise<Voto> {
    return this.http.post(VotoService.serverUrl + this.votoUrl, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Voto;
      })
      .catch(this.handleError);
  }

    eliminarVoto(id: number): Promise<any> {
    if (id) {
      return this.http.delete(VotoService.serverUrl + this.votoUrl + '/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return "";
      })
      .catch(this.handleError);
    }
  }
}

export interface Voto {
  id: number;
  concurso: number;
}
export interface VotoConcurso{
  id: number;
  concurso: ConcursoVoto;
}
export interface ConcursoVoto{
  id: number;
  descripcion: string;
  mascota: Mascota;
}
export interface CantidadVotos{
 
  concurso: number;
 cantidad: number;

}