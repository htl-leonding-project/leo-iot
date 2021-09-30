import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/core/services/notification.service';
import { Notification } from 'src/app/shared/models';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.scss']
})
export class NotificationListComponent implements OnInit {

  /**
   *the displayed Notification array
   *
   * @type {Notification[]}
   * @memberof NotificationListComponent
   */
  notifications: Notification[] = [];

  /**
   *Creates an instance of NotificationListComponent.
   * @param {NotificationService} notificationService
   * @memberof NotificationListComponent
   */
  constructor(private notificationService: NotificationService) { }

  /**
   *loading the notifications
   *
   * @memberof NotificationListComponent
   */
  ngOnInit() {
    this.notificationService.observeNotifications().subscribe(notification => {
      this.notifications.push(notification);
    });
    this.notificationService.getActiveNotifcations().subscribe(data => {
      this.notifications = [...this.notifications, ...data];
    });
  }

  /**
   *remove a notification from the list
   *
   * @param {Notification} n
   * @memberof NotificationListComponent
   */
  remove(n: Notification) {
    this.notifications.splice(this.notifications.indexOf(n), 1);
  }
}
