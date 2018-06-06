import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TcellulaireComponent } from './tcellulaire.component';

describe('TcellulaireComponent', () => {
  let component: TcellulaireComponent;
  let fixture: ComponentFixture<TcellulaireComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TcellulaireComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TcellulaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
