import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomMeasurementsComponent } from './room-measurements.component';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { AutoplayService } from 'src/app/core/services/autoplay.service';
import { LiveMeasurementService } from 'src/app/core/services/live-measurements.service';
import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { LanguageService } from 'src/app/core/services/language.service';


import {
  MqttModule,
  IMqttServiceOptions,
  MqttService
} from 'ngx-mqtt';
import {environment} from '../../../../../environments/environment';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: environment.vmUrl,
  port: 4200,
  path: '/mqtt',
  username: 'ui_client',
  password: 'ud84jc23'
};


describe('RoomMeasurementsComponent', () => {
  let component: RoomMeasurementsComponent;
  let fixture: ComponentFixture<RoomMeasurementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RoomMeasurementsComponent],
      imports: [MaterialModule, MqttModule.forRoot(MQTT_SERVICE_OPTIONS)],
      providers: [AutoplayService, MqttModule, HistoricalMeasurementService, LiveMeasurementService, LanguageService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomMeasurementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
