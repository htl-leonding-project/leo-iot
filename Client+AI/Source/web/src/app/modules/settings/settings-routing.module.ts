import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SettingsSidenavComponent, OptionsComponent } from './components';

const routes: Routes = [
  {
    path: '',
    component: SettingsSidenavComponent,
    children: [
      {
        path: '',
        component: OptionsComponent,
      },
      {
        path: ':option',
        component: OptionsComponent,
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SettingsRoutingModule { }
