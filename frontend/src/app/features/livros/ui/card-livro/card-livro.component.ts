import { Component, input, output } from '@angular/core';
import { SkeletonModule } from 'primeng/skeleton';
import { ItemBiblioteca } from '../../../../core/model/Livro';
import { DatePipe, NgOptimizedImage } from '@angular/common';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-card-livro',
  standalone: true,
  imports: [
    SkeletonModule,
    DatePipe,
    TagModule,
    ButtonModule,
    NgOptimizedImage
  ],
  templateUrl: './card-livro.component.html',
  styleUrl: './card-livro.component.css'
})
export class CardLivroComponent {
  livro = input.required<ItemBiblioteca>();
  adicionarCarrinhoEvent = output<ItemBiblioteca>();

  emitAdicionarcarrihoEvent() {
    this.adicionarCarrinhoEvent.emit(this.livro())
  }
}
