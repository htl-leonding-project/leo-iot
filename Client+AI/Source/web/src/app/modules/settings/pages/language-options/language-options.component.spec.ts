import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageOptionsComponent } from './language-options.component';
import { LanguageService } from 'src/app/core/services/language.service';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';

describe('LanguageOptionsComponent', () => {
  let component: LanguageOptionsComponent;
  let fixture: ComponentFixture<LanguageOptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LanguageOptionsComponent],
      imports: [BrowserAnimationsModule, MaterialModule, FormsModule, ReactiveFormsModule, RouterTestingModule],
      providers: [LanguageService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LanguageOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
