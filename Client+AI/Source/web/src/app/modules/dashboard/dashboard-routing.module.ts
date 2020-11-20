import { WeatherOverviewComponent } from './pages/weather-overview/weather-overview.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GridLayoutComponent, SvgViewerComponent, RoomMeasurementsComponent } from './components';
import { TramDepartureComponent, NewsComponent, NewsListComponent } from './pages';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: GridLayoutComponent
  },
  {
    path: ':area',
    component: GridLayoutComponent
  },
  {
    path: ':area/:section',
    component: GridLayoutComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
