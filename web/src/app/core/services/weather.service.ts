import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MqttService, IMqttMessage } from 'ngx-mqtt';
import { Measurement, Forecast, Weather } from 'src/app/shared/models';

@Injectable()
export class WeatherService {

  /**
   *
   * @type {string}
   * @memberof WeatherService
   */
  public message: string;


  /**
   *Retained msg, because of broker outages
   *
   * @type {Observable<Measurement>}
   * @memberof WeatherService
   */
  temperature: Observable<Measurement> = null;

  /**
 *Retained msg, because of broker outages
 *
 * @type {Observable<Measurement>}
 * @memberof WeatherService
 */
  humidity: Observable<Measurement> = null;

  /**
 *Retained msg, because of broker outages
 *
 * @type {Observable<Measurement>}
 * @memberof WeatherService
 */
  preassure: Observable<Measurement> = null;

  /**
   *Retained msg, because of broker outages
   *
   * @type {Observable<Measurement>}
   * @memberof WeatherService
   */
  windSpeed: Observable<Measurement> = null;

  /**
 *Retained msg, because of broker outages
 *
 * @type {Observable<Measurement>}
 * @memberof WeatherService
 */
  windDeg: Observable<Measurement> = null;

  /**
   * Creates an instance of WeatherService.
   * @param {MqttService} mqttService
   * @memberof WeatherService
   */
  constructor(private mqttService: MqttService) { }

  /**
   * method for observing at a specif topic
   *
   * @param {string} topic
   * @returns
   * @memberof WeatherService
   */
  observe(topic: string) {
    return this.mqttService.observe(topic);
  }

  /**
   * This method observes the temperature outdoor values published on the broker
   *
   * @param {String} value
   * @returns {Observable<Measurement>}
   * @memberof WeatherService
   */
  observeOutdoorTemperature(): Observable<Measurement> {
    if (this.temperature) {
      return this.temperature;
    }
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/outdoor/weather/actual/temperature`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.type = 'temperature';
        if (json.unit == null) {
          m.unit = '°C';
        } else {
          m.unit = json.unit;
        }
        this.temperature = of(m);
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the humidity outdoor values published on the broker
   *
   * @param {String} value
   * @returns {Observable<Measurement>}
   * @memberof WeatherService
   */
  observeOutdoorHumidity(): Observable<Measurement> {
    if (this.humidity) {
      return this.humidity;
    }
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/outdoor/weather/actual/humidity`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.type = 'humidity';
        if (json.unit == null) {
          m.unit = '%';
        } else {
          m.unit = json.unit;
        }
        this.humidity = of(m);
        observer.next(m);
      });
    });
  }

  /**
 * This method observes the air pressure outdoor values published on the broker
 *
 * @param {String} value
 * @returns {Observable<Measurement>}
 * @memberof WeatherService
 */
  observeOutdoorPressure(): Observable<Measurement> {
    if (this.preassure) {
      return this.preassure;
    }
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/outdoor/weather/actual/pressure`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.type = 'preassure';
        if (json.unit == null) {
          m.unit = 'hPa';
        } else {
          m.unit = json.unit;
        }
        this.preassure = of(m);
        observer.next(m);
      });
    });
  }

  /**
 * This method observes the wind speed outdoor values published on the broker
 *
 * @param {String} value
 * @returns {Observable<Measurement>}
 * @memberof WeatherService
 */
  observeOutdoorWindSpeed(): Observable<Measurement> {
    if (this.windSpeed) {
      return this.windSpeed;
    }
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/outdoor/weather/actual/wind_speed`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.type = 'wind speed';
        if (json.unit == null) {
          m.unit = 'km/h';
        } else {
          m.unit = json.unit;
        }
        this.windSpeed = of(m);
        observer.next(m);
      });
    });
  }

  /**
 * This method observes the wind deg outdoor values published on the broker
 *
 * @param {String} value
 * @returns {Observable<Measurement>}
 * @memberof WeatherService
 */
  observeOutdoorWindDeg(): Observable<Measurement> {
    if (this.windDeg) {
      return this.windDeg;
    }
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/outdoor/weather/actual/wind_deg`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();

        m.value = json.value;
        m.timestamp = json.timestamp;
        m.type = 'wind direction';
        if (json.unit == null) {
          m.unit = '°';
        } else {
          m.unit = json.unit;
        }
        this.windDeg = of(m);
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the given outdoor weather forecast values published on the broker
   *
   * @returns {Observable<Forecast>}
   * @memberof WeatherService
   */
  observeOutdoorForecastOpenWeatherMap(): Observable<Forecast> {
    return new Observable<Forecast>(observer => {
      this.observe('htlleonding/outdoor/weather/forecast').subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const f: Forecast = new Forecast();
        // const help = '1';

        f.cnt = json.cnt;                           // benötigt ?
        f.city = json.city;
        json.list.forEach(w => {

          const weather: Weather = new Weather();
          weather.dt = w.dt;                        // benötigt ?
          weather.temp = w.main.temp;
          weather.minTemp = w.main.temp_min;
          weather.maxTemp = w.main.temp_max;
          weather.pressure = w.main.pressure;
          weather.seaLevel = w.main.sea_level;
          weather.grndLevel = w.main.grnd_level;    // benötigt ?
          weather.humidity = w.main.humidity;
          weather.tempKf = w.main.temp_kf;          // benötigt ?

          w.weather.forEach(element => {
            weather.id = element.id;
            weather.weather = element.main;
            weather.desc = element.description;
            weather.icon = element.icon;            // benötigt ?
          });

          weather.clouds = w.clouds.all;            // benötigt ?
          weather.windspeed = w.wind.speed;
          weather.winddirection = w.wind.deg;
          weather.pod = w.sys.pod;                  // benötigt ?
          weather.timestamp = w.dt_txt;

          // ACHTUNG! zeitweise kommt kein Wert mit wahrscheinlich, wenn kein Regen ist !!!
          // weather.rain = w.rain['3h'];

          f.weather.push(weather);

          // TODO auf Tage gruppieren ?

          /*if ((weather.timestamp[8] + weather.timestamp[9]) !== help ) {

          }*/

        });
        observer.next(f);
      });
    });
  }


  // FEHLERHAFT !!!
  observeOutdoorDailyForecastArrayOpenWeatherMap(): Observable<Array<Forecast>> {
    return new Observable<Array<Forecast>>(observer => {
      this.observe('htlleonding/outdoor/weather/forecast').subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const arr: Array<Forecast> = new Array<Forecast>();
        const f: Forecast = new Forecast();
        let help = '';
        let last: Forecast = new Forecast();

        f.cnt = json.cnt;                           // benötigt ?
        f.city = json.city;

        json.list.forEach(w => {

          const weather: Weather = new Weather();
          weather.dt = w.dt;                        // benötigt ?
          weather.temp = w.main.temp;
          weather.minTemp = w.main.temp_min;
          weather.maxTemp = w.main.temp_max;
          weather.pressure = w.main.pressure;
          weather.seaLevel = w.main.sea_level;
          weather.grndLevel = w.main.grnd_level;    // benötigt ?
          weather.humidity = w.main.humidity;
          weather.tempKf = w.main.temp_kf;          // benötigt ?

          w.weather.forEach(element => {
            weather.id = element.id;
            weather.weather = element.main;
            weather.desc = element.description;
            weather.icon = element.icon;            // benötigt ?
          });

          weather.clouds = w.clouds.all;            // benötigt ?
          weather.windspeed = w.wind.speed;
          weather.winddirection = w.wind.deg;
          weather.pod = w.sys.pod;                  // benötigt ?
          weather.timestamp = w.dt_txt;

          // ACHTUNG! zeitweise kommt kein Wert mit wahrscheinlich, wenn kein Regen ist !!!
          weather.rain = w.rain['3h'];

          f.weather.push(weather);

          // TODO auf Tage gruppieren ?

          if (help === '') {
            help = weather.timestamp[8] + weather.timestamp[9];
            console.log(help);
          }

          if ((weather.timestamp[8] + weather.timestamp[9]) !== help) {
            help = weather.timestamp[8] + weather.timestamp[9];
            console.log(help);
            arr.push(last);
            f.weather = new Array<Weather>();
          }

          last = f;
        });
        arr.push(last);
        observer.next(arr);
      });
    });
  }


}
