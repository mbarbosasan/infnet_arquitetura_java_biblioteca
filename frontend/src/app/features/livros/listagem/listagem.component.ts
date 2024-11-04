import { Component, effect, inject, input } from '@angular/core';
import { delay, map, catchError, of, startWith, combineLatestWith } from 'rxjs';
import { LivrosService } from '../../../core/services/livros.service';
import { AsyncPipe } from '@angular/common';
import { SkeletonModule } from 'primeng/skeleton';
import { CardLivroComponent } from '../ui/card-livro/card-livro.component';
import { toObservable } from '@angular/core/rxjs-interop';

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
  livroService = inject(LivrosService)
  filter = input<string | null>('');
  filter$ = toObservable(this.filter);
  livros$ = this.livroService.buscarLivros().pipe(
    combineLatestWith(this.filter$),
    map(([livros, filter]) => {
      if (!filter) {
        return { loading: false, livros }
      }
      console.log(filter)
      return {
        loading: false,
        livros: livros.filter(livro => livro.titulo.toLocaleLowerCase().includes(filter.toLocaleLowerCase()))
      }
    }),
    startWith({ loading: true, livros: [] }),
  )

  constructor() {
    effect(() => {
      console.log(this.filter())
    })
  }
}
