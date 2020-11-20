import { LanguageService } from './language.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MqttService, IMqttMessage } from 'ngx-mqtt';
import { HttpClient } from '@angular/common/http';
import { Notification, NotificationCode } from 'src/app/shared/models';
import { map } from 'rxjs/operators';
import {environment} from '../../../environments/environment';

@Injectable()
export class NotificationService {

  /**
   *
   * @type {string}
   * @memberof NotificationService
   */
  public message: string;

  /**
   * this array should contain the enlish notification codes
   *
   * @type {NotificationCode[]}
   * @memberof NotificationService
   */
  public notificationCodesEnglish: NotificationCode[];

  /**
   * this array should contain the german notification codes
   *
   * @type {NotificationCode[]}
   * @memberof NotificationService
   */
  public notificationCodesGerman: NotificationCode[];

  /**
   * Creates an instance of NotificationService.
   * @param {MqttService} mqttService
   * @param {HttpClient} http
   * @param {LanguageService} languageService
   * @memberof NotificationService
   */
  constructor(private mqttService: MqttService, private http: HttpClient,
    private languageService: LanguageService) {
    this.getNotifcationCodes().subscribe(data => {
      this.notificationCodesEnglish = data;
    });
    // this.getNotifcationCodes('de').subscribe(data => this.notificationCodesGerman = data);
  }

  /**
   * method for observing at a specif topic
   *
   * @param {string} topic
   * @returns
   * @memberof NotificationService
   */
  observe(topic: string) {
    return this.mqttService.observe(topic);
  }

  /**
 * This method observes the notifications published on the broker
 *
 * @returns {Observable<Notification>}
 * @memberof NotificationService
 */
  observeNotifications(): Observable<Notification> {
    return new Observable<Notification>(observer => {
      this.observe(`htlleonding/+/+/+/+/notification`).subscribe((message: IMqttMessage) => {
        const topic: Array<string> = message.topic.split('/');
        const json = JSON.parse(message.payload.toString());
        const n: Notification = new Notification();
        const code: NotificationCode[] = this.notificationCodesEnglish.filter(c => c.code === json.code);
        n.code = code[0].code;
        n.name = code[0].name;
        n.level = code[0].level;
        n.title = code[0].title;
        n.desc = code[0].desc;
        n.resolved = json.resolved;
        n.area = topic[1];
        n.section = topic[2];
        n.position = topic[3];
        n.sensortype = topic[4];
        console.log(n);
        observer.next(n);
      });
    });
  }

  /**
   * This method returns all notificationcodes with description
   *
   * @param {String} language
   * @returns
   * @memberof NotificationService
   */
  getNotifcationCodes() {
    const language = this.languageService.getLanguage();
    return this.http.get<Array<NotificationCode>>(`${environment.vmUrl}${environment.corePathPrefix}/notification/default/all/en`)
      .pipe(
        map(json => {
          return this.toNotificationCodeArray(json);
        })
      );
  }

  /**
   *convert to array
   *
   * @param {*} json
   * @returns {NotificationCode[]}
   * @memberof NotificationService
   */
  toNotificationCodeArray(json: any): NotificationCode[] {
    const array = new Array<NotificationCode>();
    if (json) {
      json.forEach(a => {
        const notificationCode = new NotificationCode();
        notificationCode.code = a.code;
        notificationCode.name = a.name;
        notificationCode.level = a.level;
        notificationCode.title = a.title;
        notificationCode.desc = a.desc;
        array.push(notificationCode);
      });
    }
    return array;
  }

  /**
   * This method returns all active notifications
   *
   * @returns
   * @memberof NotificationService
   */
  getActiveNotifcations() {
    return this.http.get<Array<NotificationCode>>(`${environment.vmUrl}${environment.corePathPrefix}/notification/active/all`)
      .pipe(
        map(json => {
          return this.toActiveNotificationArray(json);
        })
      );
  }

  /**
   *
   *
   * @param {*} json
   * @returns {Notification[]}
   * @memberof NotificationService
   */
  toActiveNotificationArray(json: any): Notification[] {
    const array = new Array<Notification>();
    if (json) {
      json.forEach(a => {
        const notification = new Notification();
        if (a) {
          const code: NotificationCode[] = this.notificationCodesEnglish.filter(c => c.code === a.code);
          if (code) {
            notification.code = a.code;
            notification.area = a.area;
            notification.section = a.section;
            notification.position = a.position;
            notification.sensortype = a.sensorType;
            notification.name = code[0] ? code[0].name : '';
            notification.level = code[0] ? code[0].level : '';
            notification.title = code[0] ? code[0].title : '';
            notification.desc = code[0] ? code[0].desc : '';
            notification.resolved = false;
            array.push(notification);
          }
        }
      });
    }
    return array;
  }
}
