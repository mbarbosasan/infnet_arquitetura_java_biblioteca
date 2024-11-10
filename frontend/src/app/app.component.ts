import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { ItemBibliotecaService } from './core/services/item-biblioteca.service';
import { catchError, map, of, startWith } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { BaseComponent } from "./layout/base/base.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    ButtonModule,
    AsyncPipe,
    BaseComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'biblioteca-frontend';
}
