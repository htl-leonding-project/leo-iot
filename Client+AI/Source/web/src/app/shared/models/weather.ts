/**
 *
 *
 * @export
 * @class Weather
 */
export class Weather {
    dt?: number;               // benötigt ?
    temp: number;
    minTemp: number;
    maxTemp: number;
    pressure: number;
    seaLevel: number;
    grndLevel?: number;        // benötigt ?
    humidity: number;
    tempKf?: number;          // benötigt ?
    id: number;
    weather: string;
    desc: string;
    icon?: string;            // benötigt ?
    clouds?: number;           // benötigt ?
    windspeed: number;
    winddirection: number;
    pod?: number;              // benötigt ?
    timestamp: string;
    rain?: number;
}
