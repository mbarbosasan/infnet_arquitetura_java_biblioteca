import { Component, inject } from '@angular/core';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CarrinhoService } from '../../core/services/carrinho.service';
import { AsyncPipe } from '@angular/common';
import { ToggleTemaComponent } from '../../core/components/toggle-tema/toggle-tema.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    ButtonModule,
    BadgeModule,
    AsyncPipe,
    ToggleTemaComponent
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  carrinhoService = inject(CarrinhoService)
}
