import { LanguageService } from './language.service';
import { TestBed } from '@angular/core/testing';

import { NotificationService } from './notification.service';

import {
  MqttModule,
  IMqttServiceOptions,
  MqttService
} from 'ngx-mqtt';
import { HttpClientModule } from '@angular/common/http';
import {environment} from '../../../environments/environment';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: environment.vmUrl,
  port: 4200,
  path: '/mqtt',
  username: 'ui_client',
  password: 'ud84jc23'
};


describe('NotificationService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [MqttModule.forRoot(MQTT_SERVICE_OPTIONS), HttpClientModule],
    providers: [NotificationService, LanguageService]
  }));

  it('should be created', () => {
    const service: NotificationService = TestBed.get(NotificationService);
    expect(service).toBeTruthy();
  });
/*
  // Tests the method getActiveNotifcations, to return a value (not null)
  it('return value of method getActiveNotifcations should not be null', () => {
    const service: NotificationService = TestBed.get(NotificationService);

    service.getActiveNotifcations()
    .subscribe(data => {expect(data).not.toBeNull(); });
  });

  // Tests the method getNotifcationCodes, to return a value (not null)
  it('return value of method getNotifcationCodes should not be null', () => {
    const service: NotificationService = TestBed.get(NotificationService);

    service.getNotifcationCodes()
    .subscribe(data => {expect(data).not.toBeNull(); });
  });
*/
});
