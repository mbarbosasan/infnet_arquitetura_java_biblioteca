import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { ItemBiblioteca, Livro } from '../model/Livro';

@Injectable({
  providedIn: 'root'
})
export class ItemBibliotecaService {
  http = inject(HttpClient)
  constructor() { }

  buscarLivros() {
    return this.http.get<ItemBiblioteca[]>(`${environment.API_URL}/item-biblioteca`)
  }

  cadastrarItemBiblioteca(ItemBiblioteca: Livro) {
    return this.http.post<ItemBiblioteca>(`${environment.API_URL}/item-biblioteca/criar/livro/${ItemBiblioteca.autor.id}`, ItemBiblioteca)
  }
}
