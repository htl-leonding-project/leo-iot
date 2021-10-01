import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LanguageService } from '../../../../core/services/language.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricRoomDataComponent } from './historic-room-data.component';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('HistoricRoomDataComponent', () => {
  let component: HistoricRoomDataComponent;
  let fixture: ComponentFixture<HistoricRoomDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoricRoomDataComponent ],
      imports: [ MaterialModule, RouterTestingModule, BrowserAnimationsModule, FormsModule, ReactiveFormsModule ],
      providers: [HistoricalMeasurementService, LanguageService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricRoomDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
