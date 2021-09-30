import { ProgressBarComponent } from '../../../../shared/components/progress-bar/progress-bar.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TramDepartureComponent } from './tram-departure.component';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { TramService } from 'src/app/core/services/tram.service';

describe('TramDepartureComponent', () => {
  let component: TramDepartureComponent;
  let fixture: ComponentFixture<TramDepartureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TramDepartureComponent, ProgressBarComponent],
      imports: [MaterialModule],
      providers: [TramService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TramDepartureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
