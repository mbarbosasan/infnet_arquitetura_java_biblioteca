import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Livro } from '../model/Livro';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  private carrinho = new BehaviorSubject<Livro[]>([])
  carrinho$ = this.carrinho.asObservable()
  constructor() { }

  adicionarCarrinho(livro: Livro) {
    console.log('Adicionando livro ao carrinho', livro)
    this.carrinho.next([...this.carrinho.value, livro])
  }
}
