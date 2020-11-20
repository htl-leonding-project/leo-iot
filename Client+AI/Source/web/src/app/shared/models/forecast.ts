import { Weather } from './weather';

export class Forecast {
    /**
     * Count of forecast Weather measurements
     *
     * @memberof Forecast
     */
    cnt ? = 0;

    /**
     *Array of predicted Weather measurements
     *
     * @memberof Forecast
     */
    weather = new Array<Weather>();

    /**
     *The city specific to the forecast
     *
     * @memberof Forecast
     */
    city = '';
}
