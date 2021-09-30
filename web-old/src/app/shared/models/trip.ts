
/**
 * this is a class for a trip
 *
 * @export
 * @class Trip
 */
export class Trip {
    /**
     *Name of the departure point
     *
     * @type {String}
     * @memberof Trip
     */
    depName: String;

    /**
     *Name of the arrival point
     *
     * @type {String}
     * @memberof Trip
     */
    arrName: String;

    /**
     *time of departure
     *
     * @type {String}
     * @memberof Trip
     */
    depTime: String;

    /**
     *time of arrival
     *
     * @type {String}
     * @memberof Trip
     */
    arrTime: String;

    /**
     *
     *
     * @type {String}
     * @memberof Trip
     */
    timeMinute: String;

    /**
     *The duration of this trip in minutes
     *
     * @type {number}
     * @memberof Trip
     */
    duration: number;

    /**
     *The time until the trip starts in minutes
     *
     * @type {number}
     * @memberof Trip
     */
    until: number;
}
