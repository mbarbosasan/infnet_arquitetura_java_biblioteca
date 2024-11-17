import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { ItemBiblioteca, Livro, Revista } from '../model/Livro';

@Injectable({
  providedIn: 'root'
})
export class ItemBibliotecaService {
  http = inject(HttpClient)
  constructor() { }

  buscarLivros() {
    return this.http.get<ItemBiblioteca[]>(`${environment.API_URL}/item-biblioteca`)
  }

  cadastrarItemBiblioteca(itemBiblioteca: Livro) {
    return this.http.post(`${environment.API_URL}/item-biblioteca/criar/livro/${itemBiblioteca.autor.id}`, itemBiblioteca)
  }

  cadastrarRevista(itemBiblioteca: Revista) {
    return this.http.post(`${environment.API_URL}/item-biblioteca/criar/revista/${itemBiblioteca.editora.id}`, itemBiblioteca)
  }
}
