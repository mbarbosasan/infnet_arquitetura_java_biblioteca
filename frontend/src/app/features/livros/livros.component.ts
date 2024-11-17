import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { MenuModule } from 'primeng/menu';
import { ToastModule } from 'primeng/toast';
import { BehaviorSubject, debounceTime, distinctUntilChanged, startWith, Subject } from 'rxjs';
import { CadastroLivrosComponent } from './cadastro-livros/cadastro-livros.component';
import { CadastroRevistaComponent } from './cadastro-revista/cadastro-revista.component';
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
    MenuModule,
    CadastroRevistaComponent
  ],
  providers: [MessageService],
  templateUrl: './livros.component.html',
  styleUrl: './livros.component.css'
})
export class LivrosComponent implements OnInit {
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
          command: () => this.modalCadastroLivroAberto = true
        },
        {
          label: 'Cadastrar Revista',
          icon: 'pi pi-plus',
          command: () => this.modalCadastroRevistaAberto = true
        }
      ]
    }
  ]

  reloadLivros = new Subject<string>();
  reloadLivros$ = this.reloadLivros.asObservable().pipe(
    startWith(window.crypto.randomUUID())
  )

  modalCadastroLivroAberto = false;
  modalCadastroRevistaAberto = false;

  ngOnInit(): void {
    this.reloadLivros$.subscribe((e) => console.log(e))
  }

  triggerReloadLivros(type: "LIVRO" | "REVISTA") {
    this.reloadLivros.next(window.crypto.randomUUID());
    if (type === "LIVRO") {
      this.modalCadastroLivroAberto = false;
    } else {
      this.modalCadastroRevistaAberto = false;
    }
  }
}
