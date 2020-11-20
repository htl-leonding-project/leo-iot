import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelmenuComponent } from './modelmenu.component';

describe('ModelmenuComponent', () => {
  let component: ModelmenuComponent;
  let fixture: ComponentFixture<ModelmenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelmenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
