import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { SettingsRoutingModule } from './settings-routing.module';

import { OptionsComponent, SettingsSidenavComponent } from './components';
import { LanguageOptionsComponent } from './pages';

@NgModule({
  declarations: [
    SettingsSidenavComponent,
    OptionsComponent,
    LanguageOptionsComponent
  ],
  imports: [
    CommonModule,
    SettingsRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class SettingsModule { }
