import { Component, effect, inject, input } from '@angular/core';
import { delay, map, catchError, of, startWith, combineLatestWith } from 'rxjs';
import { ItemBibliotecaService } from '../../../core/services/item-biblioteca.service';
import { AsyncPipe } from '@angular/common';
import { SkeletonModule } from 'primeng/skeleton';
import { CardLivroComponent } from '../ui/card-livro/card-livro.component';
import { toObservable } from '@angular/core/rxjs-interop';
import { CarrinhoService } from '../../../core/services/carrinho.service';
import { ItemBiblioteca } from '../../../core/model/Livro';

@Component({
  selector: 'app-listagem',
  standalone: true,
  imports: [
    AsyncPipe,
    CardLivroComponent,
    SkeletonModule
  ],
  templateUrl: './listagem.component.html',
  styleUrl: './listagem.component.css'
})
export class ListagemComponent {
  livroService = inject(ItemBibliotecaService)
  carrinhoService = inject(CarrinhoService)
  filter = input<string | null>('');
  filter$ = toObservable(this.filter);
  livros$ = this.livroService.buscarLivros().pipe(
    combineLatestWith(this.filter$),
    map(([livros, filter]) => {
      if (!filter) {
        return { loading: false, error: false, livros }
      }
      console.log(filter)
      return {
        loading: false,
        error: false,
        livros: livros.filter(livro => livro.titulo.toLocaleLowerCase().includes(filter.toLocaleLowerCase()))
      }
    }),
    catchError(() => of({ loading: false, error: true, livros: null, })),
    startWith({ loading: false, error: false, livros: null }),
  )


  adicionarAoCarrinho(livro: ItemBiblioteca) {
    this.carrinhoService.adicionarCarrinho(livro)
  }
}
