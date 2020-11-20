/**
 *Model representing one Measurement
 *
 * @export
 * @class Measurement
 */
export class Measurement {
    /**
     *The measurement type
     *
     * @memberof Measurement
     */
    type?: string;

    /**
     *The numerical measurement value
     *
     * @memberof Measurement
     */
    value: any;

    /**
     *The unit of the measurement
     *
     * @memberof Measurement
     */
    unit: string;

    /**
     *Assigned section to the measurement
     *
     * @memberof Measurement
     */
    room?: string;

    /**
     *the timestamp
     *
     * @memberof Measurement
     */
    timestamp?: Date;

    /**
     *hour value
     *
     * @memberof Measurement
     */
    hour?: string;

    /**
     *month value
     *
     * @memberof Measurement
     */
    month?: string;
}
