import { MaterialModule } from '../../../../shared/modules/material.module';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationListComponent } from './notification-list.component';
import { NotificationComponent } from '../index';
import { NotificationService } from 'src/app/core/services/notification.service';
import { RouterTestingModule } from '@angular/router/testing';

import {
  MqttModule,
  IMqttServiceOptions,
  MqttService
} from 'ngx-mqtt';
import { LanguageService } from 'src/app/core/services/language.service';
import {environment} from '../../../../../environments/environment';

export const MQTT_SERVICE_OPTIONS: IMqttServiceOptions = {
  hostname: environment.vmUrl,
  port: 4200,
  path: '/mqtt',
  username: 'ui_client',
  password: 'ud84jc23'
};

describe('NotificationListComponent', () => {
  let component: NotificationListComponent;
  let fixture: ComponentFixture<NotificationListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationListComponent, NotificationComponent ],
      imports: [MaterialModule, RouterTestingModule, MqttModule.forRoot(MQTT_SERVICE_OPTIONS)],
      providers: [NotificationService, LanguageService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
