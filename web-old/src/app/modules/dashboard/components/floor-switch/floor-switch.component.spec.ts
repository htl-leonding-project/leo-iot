import { LanguageService } from '../../../../core/services/language.service';
import { HttpClientModule } from '@angular/common/http';
import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FloorSwitchComponent } from './floor-switch.component';

describe('FloorSwitchComponent', () => {
  let component: FloorSwitchComponent;
  let fixture: ComponentFixture<FloorSwitchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [ FloorSwitchComponent ],
      providers: [ HistoricalMeasurementService, LanguageService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FloorSwitchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
  });
});
