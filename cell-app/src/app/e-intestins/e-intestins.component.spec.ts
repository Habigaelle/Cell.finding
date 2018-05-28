import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EIntestinsComponent } from './e-intestins.component';

describe('EIntestinsComponent', () => {
  let component: EIntestinsComponent;
  let fixture: ComponentFixture<EIntestinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EIntestinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EIntestinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
