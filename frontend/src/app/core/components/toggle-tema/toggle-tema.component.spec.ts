import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToggleTemaComponent } from './toggle-tema.component';

describe('ToggleTemaComponent', () => {
  let component: ToggleTemaComponent;
  let fixture: ComponentFixture<ToggleTemaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ToggleTemaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToggleTemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
