import { Component, inject, input, output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { GeneroService } from '../services/genero.service';
import { finalize, map, startWith, tap } from 'rxjs';
import { EditoraService } from '../services/editora.service';
import { AsyncPipe } from '@angular/common';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { InputNumberModule } from 'primeng/inputnumber';
import { ItemBibliotecaService } from '../../../core/services/item-biblioteca.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-cadastro-revista',
  standalone: true,
  imports: [
    ButtonModule,
    InputTextModule,
    CalendarModule,
    InputTextareaModule,
    InputNumberModule,
    ReactiveFormsModule,
    DropdownModule,
    MultiSelectModule,
    AsyncPipe
  ],
  templateUrl: './cadastro-revista.component.html',
  styleUrl: './cadastro-revista.component.css'
})
export class CadastroRevistaComponent {
  #fb = inject(FormBuilder)
  #generosService = inject(GeneroService)
  #editoraService = inject(EditoraService)
  #itemBibliotecaService = inject(ItemBibliotecaService)
  #messageService = inject(MessageService)

  generosState$ = this.#generosService.buscarGeneros().pipe(
    map(generos => ({ loading: false, error: false, generos })),
    startWith({ loading: true, error: false, generos: [] })
  );

  editoraState$ = this.#editoraService.buscarEditoras().pipe(
    map(editoras => ({ loading: false, error: false, editoras })),
    startWith({ loading: true, error: false, editoras: [] })
  )

  ehModal = input<boolean>(false);
  reloadLivros = output<void>();

  loadingCadastro = false;

  form = this.#fb.group({
    titulo: ['', Validators.required],
    descricao: ['', Validators.required],
    generos: ['', Validators.required],
    editora: ['', Validators.required],
    imagem_capa: ['', Validators.required],
    issn: ['', Validators.required],
    quantidade: ['', Validators.required],
    dataPublicacao: ['', Validators.required],
  })

  cadastrarRevista(form: FormGroup) {
    return this.#itemBibliotecaService.cadastrarRevista(form.value).pipe(
      tap(() => this.loadingCadastro = true),
      finalize(() => this.loadingCadastro = false),
    ).subscribe({
      next: () => {
        this.reloadLivros.emit();
        this.form.reset();
        this.#messageService.add({ severity: 'success', summary: 'Revista cadastrada com sucesso' })
      },
      error: e => {
        console.log(e)
        this.#messageService.add({ severity: 'error', summary: 'Erro ao cadastrar revista' })
      }
    })
  }

  emitirEvento() {
    this.reloadLivros.emit();
  }
}
