import { ChangeDetectionStrategy, Component, effect, inject, input } from '@angular/core';
import { delay, map, catchError, of, startWith, combineLatestWith, merge, switchMap, tap, combineLatest, Observable } from 'rxjs';
import { ItemBibliotecaService } from '../../../core/services/item-biblioteca.service';
import { AsyncPipe } from '@angular/common';
import { SkeletonModule } from 'primeng/skeleton';
import { CardLivroComponent } from '../ui/card-livro/card-livro.component';
import { toObservable } from '@angular/core/rxjs-interop';
import { CarrinhoService } from '../../../core/services/carrinho.service';
import { ItemBiblioteca } from '../../../core/model/Livro';

@Component({
  selector: 'app-listagem-livros',
  standalone: true,
  imports: [
    AsyncPipe,
    CardLivroComponent,
    SkeletonModule
  ],
  templateUrl: './listagem-livros.component.html',
  styleUrl: './listagem-livros.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListagemComponent {
  livroService = inject(ItemBibliotecaService)
  carrinhoService = inject(CarrinhoService)
  filter = input<string | null>('');
  reloadLivros = input<string>();
  filter$ = toObservable(this.filter);
  livros$ = combineLatest(
    [toObservable(this.reloadLivros), this.filter$]
  ).pipe(
    switchMap(([_, filter]) => this.livroService.buscarLivros()),
    map(livros => ({ loading: false, error: false, livros })),
    catchError(() => of({ loading: false, error: true, livros: [] })),
    startWith({ loading: true, error: false, livros: [] }),
  )

  adicionarAoCarrinho(livro: ItemBiblioteca) {
    this.carrinhoService.adicionarCarrinho(livro)
  }
}
