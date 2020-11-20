import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { School3dComponent } from './school3d.component';

describe('School3dComponent', () => {
  let component: School3dComponent;
  let fixture: ComponentFixture<School3dComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ School3dComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(School3dComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
