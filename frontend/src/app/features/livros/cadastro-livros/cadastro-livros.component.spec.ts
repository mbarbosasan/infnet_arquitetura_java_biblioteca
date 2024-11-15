import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroLivrosComponent } from './cadastro-livros.component';

describe('CadastroComponent', () => {
  let component: CadastroLivrosComponent;
  let fixture: ComponentFixture<CadastroLivrosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroLivrosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroLivrosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
