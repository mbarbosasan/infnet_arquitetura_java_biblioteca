import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Genero } from '../../../core/model/Genero';
import { environment } from '../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

  #httpClient = inject(HttpClient)

  constructor() { }

  buscarGeneros() {
    return this.#httpClient.get<Genero[]>(`${environment.API_URL}/genero`)
  }
}
