import { DashboardModule } from '../dashboard/dashboard.module';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NavigationRoutingModule } from './navigation-routing.module';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { SharedModule } from 'src/app/shared/shared.module';

import {
  SidenavComponent,
  NotificationListComponent,
  NotificationComponent
} from './components';

@NgModule({
  declarations: [
    SidenavComponent,
    NotificationListComponent,
    NotificationComponent
  ],
  imports: [
    CommonModule,
    NavigationRoutingModule,
    MaterialModule,
    SharedModule
  ]
})
export class NavigationModule { }
