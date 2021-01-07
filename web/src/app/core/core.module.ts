import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { environment } from 'src/environments/environment';

import { HistoricalMeasurementService } from './services/historical-measurements.service';
import { LiveMeasurementService } from './services/live-measurements.service';
import { NotificationService } from './services/notification.service';
import { TramService } from './services/tram.service';
import { WeatherService } from './services/weather.service';
import { LanguageService } from './services/language.service';
import { AutoplayService } from './services/autoplay.service';

import {
  MqttModule,
  IMqttServiceOptions
} from 'ngx-mqtt';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: `${environment.mqttUrl}`,
  path: `${environment.mqttPathPrefix}${environment.mqttPath}`,
  protocol: 'wss',
  port: environment.mqttPort,
  username: 'ui_client',
  password: 'ud84jc23'
};

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MqttModule.forRoot(MQTT_SERVICE_OPTIONS)
  ],
  declarations: [],
  providers: [
    AutoplayService,
    HistoricalMeasurementService,
    LanguageService,
    LiveMeasurementService,
    NotificationService,
    TramService,
    WeatherService
  ]
})
export class CoreModule { }

