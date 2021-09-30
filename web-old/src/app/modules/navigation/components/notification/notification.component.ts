import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent {

  /**
   *Type of the notification
   *
   * @type {String}
   * @memberof NotificationComponent
   */
  @Input() type: String;

  /**
   *Title of the notification
   *
   * @type {String}
   * @memberof NotificationComponent
   */
  @Input() title: String;

  /**
   *Body containing the notification description
   *
   * @type {String}
   * @memberof NotificationComponent
   */
  @Input() body: String;

  /**
   *the displayName of the area, the notification is from
   *
   * @type {String}
   * @memberof NotificationComponent
   */
  @Input() areaName: String;

  /**
   *the displayName of the section, the notification is from
   *
   * @type {String}
   * @memberof NotificationComponent
   */
  @Input() sectionName: String;

  /**
   *Event when the ok button has been clicked
   *
   * @type {EventEmitter<void>}
   * @memberof NotificationComponent
   */
  @Output() close: EventEmitter<void> = new EventEmitter<void>();

  /**
   *Creates an instance of NotificationComponent.
   * @memberof NotificationComponent
   */
  constructor(private router: Router) { }

  /**
   *Navigates to the area and section of the notification
   *
   * @param {string} area name of the area the notification is from
   * @param {string} section name of the section the notification is from
   * @memberof NotificationComponent
   */
  navigate(area: String, section: String) {
    this.router.navigate(['dashboard', area, section]);
  }
}
