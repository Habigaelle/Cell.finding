import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TissuComponent } from './tissu.component';

describe('TissuComponent', () => {
  let component: TissuComponent;
  let fixture: ComponentFixture<TissuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TissuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TissuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
