import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {CoreModule} from './core/core.module';
import {NavigationModule} from './modules/navigation/navigation.module';
import {School3dComponent} from './3d/school3d/school3d.component';
import {ModelmenuComponent} from './3d/school3d/modelmenu/modelmenu.component';
import {VideoFeedComponent} from './3d/video-feed/video-feed.component';
import {MatButtonModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    School3dComponent,
    ModelmenuComponent,
    VideoFeedComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CoreModule,
    NavigationModule,
    MatButtonModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
