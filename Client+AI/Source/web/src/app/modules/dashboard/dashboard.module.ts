import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DashboardRoutingModule} from './dashboard-routing.module';
import {MaterialModule} from 'src/app/shared/modules/material.module';
import {SharedModule} from 'src/app/shared/shared.module';

import {
  SvgViewerComponent,
  GridLayoutComponent,
  RoomMeasurementsComponent,
  HistoricRoomDataComponent,
  FloorSwitchComponent
} from './components';

import {
  TramDepartureComponent,
  NewsComponent,
  WeatherOverviewComponent,
  NewsListComponent
} from './pages';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    SvgViewerComponent,
    GridLayoutComponent,
    RoomMeasurementsComponent,
    HistoricRoomDataComponent,
    TramDepartureComponent,
    NewsComponent,
    WeatherOverviewComponent,
    FloorSwitchComponent,
    NewsListComponent,
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MaterialModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class DashboardModule {
}
