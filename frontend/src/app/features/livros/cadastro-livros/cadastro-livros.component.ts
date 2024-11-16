import { Component, inject, input, OnInit, output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { MultiSelectModule } from 'primeng/multiselect';
import { AutorService } from '../services/autor.service';
import { GeneroService } from '../services/genero.service';
import { DropdownModule } from 'primeng/dropdown';
import { AsyncPipe } from '@angular/common';
import { finalize, map, startWith, tap } from 'rxjs';
import { ItemBibliotecaService } from '../../../core/services/item-biblioteca.service';
import { MessageService } from 'primeng/api';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputNumberModule } from 'primeng/inputnumber';

@Component({
  selector: 'app-cadastro-livros',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    ButtonModule,
    CalendarModule,
    MultiSelectModule,
    DropdownModule,
    AsyncPipe,
    InputTextareaModule,
    InputNumberModule
  ],
  templateUrl: './cadastro-livros.component.html',
  styleUrl: './cadastro-livros.component.css'
})
export class CadastroLivrosComponent {
  #formBuilder = inject(FormBuilder);
  #generosService = inject(GeneroService);
  #itemBibliotecaService = inject(ItemBibliotecaService);
  #autorService = inject(AutorService);
  #messageService = inject(MessageService);

  ehModal = input<boolean>(false);
  reloadLivros = output<void>();
  loadingCadastro = false;

  autorState$ = this.#autorService.buscarAutores().pipe(
    map(autores => ({ loading: false, error: false, autores })),
    startWith({ loading: true, error: false, autores: [] })
  );
  generosState$ = this.#generosService.buscarGeneros().pipe(
    map(generos => ({ loading: false, error: false, generos })),
    startWith({ loading: true, error: false, generos: [] })
  );

  form = this.#formBuilder.group({
    titulo: ['', Validators.required],
    autor: ['', Validators.required],
    descricao: ['', Validators.required],
    generos: ['', Validators.required],
    imagem_capa: ['', [Validators.required, Validators.pattern(/^(http|https):\/\/[^ "]+$/)]],
    quantidade: ['', Validators.required, Validators.min(1)],
    dataPublicacao: ['', Validators.required],
    isbn: ['', Validators.required]
  })


  cadastrarLivro(form: FormGroup) {
    return this.#itemBibliotecaService.cadastrarItemBiblioteca(form.value).pipe(
      tap(() => this.loadingCadastro = false),
      finalize(() => this.loadingCadastro = false)
    ).subscribe({
      next: () => {
        this.reloadLivros.emit();
        this.form.reset();
        this.#messageService.add({ severity: 'success', summary: 'Livro cadastrado com sucesso' })
      },
      error: (e) => this.#messageService.add({ severity: 'error', summary: 'Erro ao cadastrar livro', detail: e.message })
    })
  }
}