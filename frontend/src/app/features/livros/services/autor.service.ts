import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { Genero } from '../../../core/model/Genero';

@Injectable({
  providedIn: 'root'
})
export class AutorService {
  #httpClient = inject(HttpClient)
  constructor() { }

  buscarAutores() {
    return this.#httpClient.get<Genero[]>(`${environment.API_URL}/autor`)
  }
}
