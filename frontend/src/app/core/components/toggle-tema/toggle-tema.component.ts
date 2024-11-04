import { DOCUMENT } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-toggle-tema',
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './toggle-tema.component.html',
  styleUrl: './toggle-tema.component.scss'
})
export class ToggleTemaComponent implements OnInit {

  #document = inject(DOCUMENT);
  isDarkMode = false;

  ngOnInit() {
    const theme = localStorage.getItem('theme');
    if (theme === 'dark') {
      this.toggleLightDark();
    }
  }

  toggleLightDark() {
    const linkElement = this.#document.getElementById(
      'app-theme'
    ) as HTMLLinkElement;
    if (linkElement.href.includes('light')) {
      localStorage.setItem('theme', 'dark');
      linkElement.href = 'theme-dark.css';
      this.isDarkMode = true;
    } else {
      linkElement.href = 'theme-light.css';
      localStorage.setItem('theme', 'light');
      this.isDarkMode = false;
    }
  }
}
