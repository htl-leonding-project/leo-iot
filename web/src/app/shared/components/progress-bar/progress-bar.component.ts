import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-progress-bar',
  templateUrl: './progress-bar.component.html',
  styleUrls: ['./progress-bar.component.scss']
})
export class ProgressBarComponent implements OnChanges {

  /**
   *Current percentage value has to be of `0 <= value <= 100`. Defaults to 0
   *
   * @memberof ProgressBarComponent
   */
  @Input() value = 0;

  /**
   *Emits when the progress bar value has reached 100%
   *
   * @type {EventEmitter<void>}
   * @memberof ProgressBarComponent
   */
  @Output() finished: EventEmitter<void> = new EventEmitter();

  /**
   *Creates an instance of ProgressBarComponent.
   * @memberof ProgressBarComponent
   */
  constructor() { }

  /**
   *Listens for and validates value changes
   *
   * @param {SimpleChanges} changes
   * @memberof ProgressBarComponent
   */
  ngOnChanges(changes: SimpleChanges) {
    const change = changes['value'];
    if (change.currentValue >= 0) {
      if (change.currentValue >= 100) {
        this.value = 100;
        this.finished.emit();
      } else {
        this.value = change.currentValue;
      }
    } else {
      console.warn('Invalid progress bar value');
    }
  }
}
