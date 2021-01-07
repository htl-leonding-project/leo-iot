import { TestBed } from '@angular/core/testing';

import { LiveMeasurementService } from './live-measurements.service';

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

describe('LiveMeasurementService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [MqttModule.forRoot(MQTT_SERVICE_OPTIONS)],
    providers: [LiveMeasurementService, MqttService]
  }));

  it('should be created', () => {
    const service: LiveMeasurementService = TestBed.get(LiveMeasurementService);
    expect(service).toBeTruthy();
  });
});
