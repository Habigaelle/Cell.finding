import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EBoucheComponent } from './e-bouche.component';

describe('EBoucheComponent', () => {
  let component: EBoucheComponent;
  let fixture: ComponentFixture<EBoucheComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EBoucheComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EBoucheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
