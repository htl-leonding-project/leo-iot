import { TestBed } from '@angular/core/testing';

import { WeatherService } from './weather.service';

import {
  MqttModule,
  IMqttServiceOptions,
  MqttService
} from 'ngx-mqtt';
import {environment} from '../../../environments/environment';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: environment.vmUrl,
  port: 4200,
  path: '/mqtt',
  username: 'ui_client',
  password: 'ud84jc23'
};


describe('WeatherService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [MqttModule.forRoot(MQTT_SERVICE_OPTIONS)],
    providers: [ WeatherService ]
  }));

  it('should be created', () => {
    const service: WeatherService = TestBed.get(WeatherService);
    expect(service).toBeTruthy();
  });
});
