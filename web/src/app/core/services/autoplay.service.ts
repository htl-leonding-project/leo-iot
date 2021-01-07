import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { autoplayConfig } from 'src/app/config/glider';
import { AutoplayState } from 'src/app/shared/models';

@Injectable()
export class AutoplayService {

  /**
   *Object containing the config object
   *
   * @type {BehaviorSubject<AutoplayState>}
   * @memberof AutoplayService
   */
  _autoplay: BehaviorSubject<AutoplayState> = new BehaviorSubject<AutoplayState>(null);

  /**
   * Creates an instance of AutoplayService.
   * @memberof AutoplayService
   */
  constructor() {
    this.setAutoplayState();
  }

  /**
   * sets the autoplay state
   *
   * @memberof AutoplayService
   */
  setAutoplayState() {
    this._autoplay.next(autoplayConfig);
  }
}
