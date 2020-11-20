import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './../../../../shared/modules/material.module';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionsComponent } from './options.component';
import { LanguageOptionsComponent } from '../../pages';
import { RouterTestingModule } from '@angular/router/testing';
import { LanguageService } from 'src/app/core/services/language.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('OptionsComponent', () => {
    let component: OptionsComponent;
    let fixture: ComponentFixture<OptionsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [OptionsComponent, LanguageOptionsComponent],
            imports: [BrowserAnimationsModule, MaterialModule, FormsModule, ReactiveFormsModule, RouterTestingModule],
            providers: [LanguageService]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(OptionsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
