import { MeasurementType } from './measurementType';

/**
 *Model representing a room
 *
 * @export
 * @class Section
 */
export class Section {
    /**
     *the name of the room
     *
     * @memberof Section
     */
    name: string;

    /**
     *all sensors in this room
     *
     * @memberof Section
     */
    sensors?: MeasurementType[];
}
