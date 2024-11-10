import { TestBed } from '@angular/core/testing';

import { ItemBibliotecaService } from './item-biblioteca.service';

describe('LivrosService', () => {
  let service: ItemBibliotecaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemBibliotecaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
