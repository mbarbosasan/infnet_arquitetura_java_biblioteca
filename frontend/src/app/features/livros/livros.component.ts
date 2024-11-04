import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { ListagemComponent } from "./listagem/listagem.component";
import { AsyncPipe } from '@angular/common';
import { debounceTime, distinctUntilChanged } from 'rxjs';

@Component({
  selector: 'app-livros',
  standalone: true,
  imports: [
    AsyncPipe,
    ListagemComponent,
    InputTextModule,
    IconFieldModule,
    InputIconModule,
    ReactiveFormsModule
  ],
  templateUrl: './livros.component.html',
  styleUrl: './livros.component.css'
})
export class LivrosComponent {
  filter = new FormControl('');
  filter$ = this.filter.valueChanges.pipe(
    debounceTime(300),
    distinctUntilChanged()
  );
  constructor() {
  }
}
