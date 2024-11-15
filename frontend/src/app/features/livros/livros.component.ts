import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { ListagemComponent } from "./listagem-livros/listagem-livros.component";
import { AsyncPipe } from '@angular/common';
import { debounceTime, distinctUntilChanged, Subject, tap } from 'rxjs';
import { DialogModule } from 'primeng/dialog';
import { CadastroLivrosComponent } from './cadastro-livros/cadastro-livros.component';
import { ButtonModule } from 'primeng/button';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-livros',
  standalone: true,
  imports: [
    AsyncPipe,
    ListagemComponent,
    InputTextModule,
    IconFieldModule,
    InputIconModule,
    ReactiveFormsModule,
    ButtonModule,
    DialogModule,
    CadastroLivrosComponent,
    ToastModule
  ],
  providers: [MessageService],
  templateUrl: './livros.component.html',
  styleUrl: './livros.component.css'
})
export class LivrosComponent {
  filter = new FormControl('');
  filter$ = this.filter.valueChanges.pipe(
    debounceTime(300),
    distinctUntilChanged()
  );

  reloadLivros = new Subject<void>();
  
  modalCadastroAberto = false;
  constructor() {
  }
  
}
