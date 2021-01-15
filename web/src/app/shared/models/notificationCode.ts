/**
 *
 *
 * @export
 * @class NotificationCode
 */

export class NotificationCode {
    /**
     *code of the notification
     *
     * @type {number}
     * @memberof NotificationCode
     */
    code: number;

    /**
     *The notifications name
     *
     * @type {string}
     * @memberof NotificationCode
     */
    name?: string;

    /**
     *Level of the notification
     *
     * @type {string}
     * @memberof NotificationCode
     */
    level?: string;

    /**
     *The title of the notification
     *
     * @type {string}
     * @memberof NotificationCode
     */
    title?: string;

    /**
     *Additional description of the notifivcation
     *
     * @type {string}
     * @memberof NotificationCode
     */
    desc?: string;
}
