import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MaterialModule } from './modules/material.module';

import {
  NotificationToggleComponent,
  ProgressBarComponent,
  SettingsButtonComponent,
  FooterTextComponent
} from './components';

@NgModule({
  declarations: [
    NotificationToggleComponent,
    ProgressBarComponent,
    SettingsButtonComponent,
    FooterTextComponent
  ],
  exports: [
    NotificationToggleComponent,
    ProgressBarComponent,
    SettingsButtonComponent,
    FooterTextComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule
  ],
})
export class SharedModule { }
