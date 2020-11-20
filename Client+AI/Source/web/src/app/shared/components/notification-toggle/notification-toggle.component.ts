import { Component, Output, EventEmitter } from '@angular/core';
import { trigger, transition, style, animate, state } from '@angular/animations';


@Component({
  selector: 'app-notification-toggle',
  templateUrl: './notification-toggle.component.html',
  styleUrls: ['./notification-toggle.component.scss'],
  animations: [

    trigger('transformArrowIcon', [
      state('open', style({
        opacity: 1,
      })),
      state('close', style({
        opacity: 0,
        transform: 'rotate(360deg)'
      })),
      transition('open => close', [
      animate('200ms cubic-bezier(0.4, 0.0, 0.2, 1)')
      ])
    ]),
  ]
})
export class NotificationToggleComponent {

  /**
   *Toggle click event
   *
   * @type {EventEmitter<void>}
   * @memberof NotificationToggleComponent
   */
  @Output() clicked: EventEmitter<void> = new EventEmitter<void>();

  /**
   *State of the toggle
   *
   * @memberof NotificationToggleComponent
   */
  state = false;

  /**
   *Creates an instance of NotificationToggleComponent.
   * @memberof NotificationToggleComponent
   */
  constructor() { }

  /**
   *Makes the event emitter emit a void on button click and changes the animation state
   *
   * @memberof NotificationToggleComponent
   */
  emitAndAnimate() {
    this.state = !this.state;
    this.clicked.emit();
  }
}
