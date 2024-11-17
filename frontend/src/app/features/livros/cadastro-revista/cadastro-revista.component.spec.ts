import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroRevistaComponent } from './cadastro-revista.component';

describe('CadastroRevistaComponent', () => {
  let component: CadastroRevistaComponent;
  let fixture: ComponentFixture<CadastroRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
