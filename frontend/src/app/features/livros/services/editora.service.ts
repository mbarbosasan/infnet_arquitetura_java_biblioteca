import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { Editora } from '../../../core/model/Editora';

@Injectable({
  providedIn: 'root'
})
export class EditoraService {
  #http = inject(HttpClient)

  buscarEditoras() {
    return this.#http.get<Editora[]>(`${environment.API_URL}/editora`)
  }
}
