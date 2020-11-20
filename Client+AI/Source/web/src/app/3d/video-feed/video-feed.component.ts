import {Component, Input, OnDestroy, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import tracking from 'tracking/build/tracking';

@Component({
  selector: 'app-video-feed',
  templateUrl: './video-feed.component.html',
  styleUrls: ['./video-feed.component.scss']
})
export class VideoFeedComponent {

  @Input()
  public url = 'localhost';
  public port = 8081;

  public PHOTO_ENDING = '/mjpegs';
  public streaming = false;

  constructor(private http: HttpClient) {
  }

  start() {
    this.http.get(`${this.url}:${this.port}/start`)
      .subscribe(value =>
        setTimeout(() => this.streaming = true, (1000))
      );
  }

  stop() {
    this.http.get(`${this.url}:${this.port}/stop`)
      .subscribe(value =>
        this.streaming = false
      );
  }

  pictureUrl() {
    return `${this.url}:${this.PHOTO_ENDING}`;
  }
}
