/**
 *
 *
 * @export
 * @class Notification
 */

export class Notification {
    /**
     *Notification code
     *
     * @type {number}
     * @memberof Notification
     */
    code: number;

    /**
     *Notification name
     *
     * @type {string}
     * @memberof Notification
     */
    name: string;

    /**
     *Level of the notification
     *
     * @type {string}
     * @memberof Notification
     */
    level: string;

    /**
     *Title of the notfication
     *
     * @type {string}
     * @memberof Notification
     */
    title: string;

    /**
     *Floor or area the notification is from
     *
     * @type {string}
     * @memberof Notification
     */
    area: string;

    /**
     *Section or room the notification is from
     *
     * @type {string}
     * @memberof Notification
     */
    section: string;

    /**
     *Position the notification is from
     *
     * @type {string}
     * @memberof Notification
     */
    position: string;

    /**
     *Sensortype the notfication is from
     *
     * @type {string}
     * @memberof Notification
     */
    sensortype: string;

    /**
     *Description of the notification
     *
     * @type {string}
     * @memberof Notification
     */
    desc: string;

    /**
     *resolve status of notification
     *
     * @type {boolean}
     * @memberof Notification
     */
    resolved: boolean;
}
