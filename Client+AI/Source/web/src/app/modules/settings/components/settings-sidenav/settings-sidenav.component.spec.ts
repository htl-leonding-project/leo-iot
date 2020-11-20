import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsSidenavComponent } from './settings-sidenav.component';
import { RouterTestingModule } from '@angular/router/testing';
import { MaterialModule } from 'src/app/shared/modules/material.module';

describe('SettingsSidenavComponent', () => {
  let component: SettingsSidenavComponent;
  let fixture: ComponentFixture<SettingsSidenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SettingsSidenavComponent],
      imports: [BrowserAnimationsModule, RouterTestingModule, MaterialModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsSidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
