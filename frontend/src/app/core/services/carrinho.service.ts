import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ItemBiblioteca } from '../model/Livro';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  private carrinho = new BehaviorSubject<ItemBiblioteca[]>([])
  carrinho$ = this.carrinho.asObservable()
  constructor() { }

  adicionarCarrinho(livro: ItemBiblioteca) {
    console.log('Adicionando livro ao carrinho', livro)
    this.carrinho.next([...this.carrinho.value, livro])
  }
}
