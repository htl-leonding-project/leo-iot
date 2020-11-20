import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsButtonComponent } from './settings-button.component';
import { MaterialModule } from '../../modules/material.module';
import { RouterTestingModule } from '@angular/router/testing';

describe('SettingsButtonComponent', () => {
  let component: SettingsButtonComponent;
  let fixture: ComponentFixture<SettingsButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SettingsButtonComponent],
      imports: [MaterialModule, RouterTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
