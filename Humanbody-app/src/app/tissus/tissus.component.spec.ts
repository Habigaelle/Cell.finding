import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TissusComponent } from './tissus.component';

describe('TissusComponent', () => {
  let component: TissusComponent;
  let fixture: ComponentFixture<TissusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TissusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TissusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
