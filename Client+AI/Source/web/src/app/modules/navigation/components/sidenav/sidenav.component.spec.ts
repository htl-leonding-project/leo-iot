import { SettingsButtonComponent } from './../../../../shared/components/settings-button/settings-button.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavComponent } from './sidenav.component';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { NotificationListComponent, NotificationComponent } from '..';
import { NotificationToggleComponent } from 'src/app/shared/components';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NotificationService } from 'src/app/core/services/notification.service';

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

describe('SidenavComponent', () => {
  let component: SidenavComponent;
  let fixture: ComponentFixture<SidenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SidenavComponent,
        NotificationListComponent,
        NotificationComponent,
        NotificationToggleComponent,
        SettingsButtonComponent
      ],
      imports: [BrowserAnimationsModule, RouterTestingModule, MaterialModule, MqttModule.forRoot(MQTT_SERVICE_OPTIONS)],
      providers: [NotificationService, LanguageService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
