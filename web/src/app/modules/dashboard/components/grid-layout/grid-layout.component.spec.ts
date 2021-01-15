import { LiveMeasurementService } from 'src/app/core/services/live-measurements.service';
import { AutoplayService } from '../../../../core/services/autoplay.service';
import { LanguageService } from '../../../../core/services/language.service';
import { FloorSwitchComponent } from '../floor-switch/floor-switch.component';
import { HistoricalMeasurementService } from '../../../../core/services/historical-measurements.service';
import { HistoricRoomDataComponent } from '../historic-room-data/historic-room-data.component';
import { SvgViewerComponent } from '../svg-viewer/svg-viewer.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GridLayoutComponent } from './grid-layout.component';
import { RoomMeasurementsComponent } from '../room-measurements/room-measurements.component';
import { TramDepartureComponent, WeatherOverviewComponent, NewsListComponent, NewsComponent } from '../../pages';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { ProgressBarComponent, FooterTextComponent } from 'src/app/shared/components';


import {
  MqttModule,
  IMqttServiceOptions,
  MqttService
} from 'ngx-mqtt';
import { TramService } from 'src/app/core/services/tram.service';
import { WeatherService } from 'src/app/core/services/weather.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {environment} from '../../../../../environments/environment';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: environment.vmUrl,
  port: 4200,
  path: '/mqtt',
  username: 'ui_client',
  password: 'ud84jc23'
};


describe('GridLayoutComponent', () => {
  let component: GridLayoutComponent;
  let fixture: ComponentFixture<GridLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        GridLayoutComponent,
        SvgViewerComponent,
        RoomMeasurementsComponent,
        HistoricRoomDataComponent,
        TramDepartureComponent,
        WeatherOverviewComponent,
        NewsListComponent,
        FloorSwitchComponent,
        ProgressBarComponent,
        NewsComponent,
        FooterTextComponent
      ],
      imports: [
        MqttModule.forRoot(MQTT_SERVICE_OPTIONS),
        MaterialModule,
        RouterTestingModule,
        FormsModule,
        ReactiveFormsModule
      ],
      providers: [
        HistoricalMeasurementService,
        WeatherService,
        LanguageService,
        TramService,
        AutoplayService,
        LiveMeasurementService,
        MqttService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GridLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
