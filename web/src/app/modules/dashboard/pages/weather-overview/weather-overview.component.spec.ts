import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherOverviewComponent } from './weather-overview.component';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { WeatherService } from 'src/app/core/services/weather.service';

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

describe('WeatherOverviewComponent', () => {
  let component: WeatherOverviewComponent;
  let fixture: ComponentFixture<WeatherOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WeatherOverviewComponent],
      imports: [MaterialModule, MqttModule.forRoot(MQTT_SERVICE_OPTIONS)],
      providers: [MqttModule, WeatherService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
