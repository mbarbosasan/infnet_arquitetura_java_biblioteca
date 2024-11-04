import { Component, input, output } from '@angular/core';
import { SkeletonModule } from 'primeng/skeleton';
import { Livro } from '../../../../core/model/Livro';
import { DatePipe } from '@angular/common';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-card-livro',
  standalone: true,
  imports: [
    SkeletonModule,
    DatePipe,
    TagModule,
    ButtonModule
  ],
  templateUrl: './card-livro.component.html',
  styleUrl: './card-livro.component.css'
})
export class CardLivroComponent {
  livro = input.required<Livro>();
  adicionarCarrinhoEvent = output<Livro>();

  emitAdicionarcarrihoEvent() {
    this.adicionarCarrinhoEvent.emit(this.livro())
  }
}
