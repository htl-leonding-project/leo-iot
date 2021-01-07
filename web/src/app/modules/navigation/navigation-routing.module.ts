import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SidenavComponent } from './components';

const routes: Routes = [
  {
    path: '',
    component: SidenavComponent,
    children: [
      {
        path: 'dashboard',
        loadChildren: 'src/app/modules/dashboard/dashboard.module#DashboardModule',
      },
      {
        path: 'settings',
        loadChildren: 'src/app/modules/settings/settings.module#SettingsModule',
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NavigationRoutingModule { }
