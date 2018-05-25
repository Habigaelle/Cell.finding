import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EncyclopedieComponent } from './encyclopedie.component';

describe('EncyclopedieComponent', () => {
  let component: EncyclopedieComponent;
  let fixture: ComponentFixture<EncyclopedieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EncyclopedieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EncyclopedieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
