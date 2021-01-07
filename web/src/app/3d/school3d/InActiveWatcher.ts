import {timeout} from 'rxjs/operators';

/**
 * this class Execute A Callback after a specific Time if you did not stop or reset it in the meantime.
 */
export class InActiveWatcher {
  timeout;
  finished = false;

  constructor(public callbackStart, public callbackEnd , public time) {
    this.start();
  }

  /**
    start the timeout. If Finished it will execute the callback and stop.
   */
  start() {
    this.timeout = setTimeout(() => {
      this.callbackStart();
      this.finished = true;
      this.stop();
    }, this.time);
  }

  /**
   * reset the timeout to the starting time.
   */
  reset() {
    if (this.finished) {
      this.callbackEnd();
    }

    this.stop();
    this.start();
  }

  /**
   * Stop the Timout
   */
  stop() {
    clearTimeout(this.timeout);
  }
}
