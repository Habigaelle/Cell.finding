import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppareilDigestifComponent } from './appareil-digestif.component';

describe('AppareilDigestifComponent', () => {
  let component: AppareilDigestifComponent;
  let fixture: ComponentFixture<AppareilDigestifComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppareilDigestifComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppareilDigestifComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
