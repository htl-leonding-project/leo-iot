import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {School3dComponent} from './3d/school3d/school3d.component';
import {VideoFeedComponent} from './3d/video-feed/video-feed.component';

const routes: Routes = [
  {
    path: 'dashboard',
    pathMatch: 'full',
    redirectTo: 'dashboard'
  },
  {
    path: '3d',
      component: School3dComponent
  }, {
    path: 'video',
      component: VideoFeedComponent
  }, {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'dashboard'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
