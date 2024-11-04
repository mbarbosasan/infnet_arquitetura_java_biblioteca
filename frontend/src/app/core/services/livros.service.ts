import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { Livro } from '../model/Livro';

@Injectable({
  providedIn: 'root'
})
export class LivrosService {
  http = inject(HttpClient)
  constructor() { }

  buscarLivros() {
    return this.http.get<Livro[]>(`${environment.API_URL}/livro`)
  }
}
