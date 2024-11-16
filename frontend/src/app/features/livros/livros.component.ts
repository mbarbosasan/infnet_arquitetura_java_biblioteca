import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { MenuModule } from 'primeng/menu';
import { ToastModule } from 'primeng/toast';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';
import { CadastroLivrosComponent } from './cadastro-livros/cadastro-livros.component';
import { ListagemComponent } from "./listagem-livros/listagem-livros.component";

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
    ToastModule,
    MenuModule
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

  menuItems: MenuItem[] = [
    {
      items: [
        {
          label: 'Cadastrar Livro',
          icon: 'pi pi-plus',
          command: () => this.modalCadastroAberto = true
        },
        {
          label: 'Cadastrar Revista',
          icon: 'pi pi-plus',
        }
      ]
    }
  ]

  reloadLivros = new Subject<void>();

  modalCadastroAberto = false;
  constructor() {
  }

}
