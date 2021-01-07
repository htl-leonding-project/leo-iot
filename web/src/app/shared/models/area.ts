import { Section } from './section';

/**
 *Area model representing on floor of the school building
 *
 * @export
 * @class Area
 */
export class Area {
    /**
     *The areas name
     *
     * @memberof Area
     */
    name = '';

    /**
     *The areas displayName
     *
     * @memberof Area
     */
    displayName = '';

    /**
     *All sectionf of the area
     *
     * @memberof Area
     */
    sections = new Array<Section>();
}
