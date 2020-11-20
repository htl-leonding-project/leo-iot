import {Injectable, OnDestroy} from '@angular/core';
import {Observable, BehaviorSubject, throwError} from 'rxjs';
import {MqttService, IMqttMessage} from 'ngx-mqtt';
import {Area, Section, Measurement} from 'src/app/shared/models';
import {MeasurementTypeAndValue} from '../../modules/dashboard/components';
import {ParseArgumentException} from '@angular/cli/models/parser';
import {ModelAction} from '../../3d/school3d/ModelController/ModelAction';

@Injectable()
export class LiveMeasurementService {

  /**
   *
   * @type {string}
   * @memberof LiveMeasurementService
   */
  public message: string;

  /**
   * Creates an instance of LiveMeasurementService.
   * @param {MqttService} mqttService
   * @memberof LiveMeasurementService
   */
  constructor(private mqttService: MqttService) {
  }

  /**
   * method for observing at a specif topic
   *
   * @param {string} topic
   * @returns
   * @memberof LiveMeasurementService
   */
  observe(topic: string) {
    return this.mqttService.observeRetained(topic);
  }

  /**
   * This method observes the co2 values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observeCo2(area: Area, section: Section, position: string): Observable<Measurement> {
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/co2`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.unit = 'PPM';
        // m.unit = json.unit;
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the temperature values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observeTemperature(area: Area, section: Section, position: string): Observable<Measurement> {
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/temperature`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.unit = '°C';
        // m.unit = json.unit;
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the humidity values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observeHumidity(area: Area, section: Section, position: string): Observable<Measurement> {
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/humidity`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.unit = '%';
        // m.unit = json.unit;
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the brightness values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observeBrightness(area: Area, section: Section, position: string): Observable<Measurement> {
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/light`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = Math.round(json.value * 100) / 100;
        m.timestamp = json.timestamp;
        m.unit = 'LUX';
        // m.unit = json.unit;
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the loudness values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observeLoudness(area: Area, section: Section, position: string): Observable<Measurement> {
    if (section.name === '123') {
      return new Observable<Measurement>(observer => {
        this.observe(`htlleonding/${area.name}/${section.name}/${position}/db`).subscribe((message: IMqttMessage) => {
          const json = JSON.parse(message.payload.toString());
          const m: Measurement = new Measurement();
          m.value = Math.round(json.value * 100) / 100;
          m.timestamp = json.timestamp;
          m.unit = 'DB';
          // m.unit = json.unit;
          // console.log(json)
          observer.next(m);
        });
      });
    } else {
      return new Observable<Measurement>(observer => {
        this.observe(`htlleonding/${area.name}/${section.name}/${position}/db`).subscribe((message: IMqttMessage) => {
          const json = JSON.parse(message.payload.toString());
          const m: Measurement = new Measurement();
          m.value = Math.round(json.value * 100) / 100;
          m.timestamp = json.timestamp;
          m.unit = 'DB';
          // m.unit = json.unit;
          // console.log(json)
          observer.next(m);
        });
      });
    }
  }

  /**
   * This method observes the power values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observePower(area: Area, section: Section, position: string): Observable<Measurement> {
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/Power`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        if (json.value > 700) {
          m.value = 'ON';
        } else {
          m.value = 'OFF';
        }
        m.timestamp = json.timestamp;
        // m.unit = 'W';
        // m.unit = json.unit;
        observer.next(m);
      });
    });
  }

  /**
   * This method observes the power values published on the broker
   *
   * @param {Area} area
   * @param {Section} section
   * @param {string} position
   * @returns {Observable<Measurement>}
   * @memberof LiveMeasurementService
   */
  observeWebcam(area: Area, section: Section, position: string): Observable<Measurement> {
    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/webcam`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.unit = 'IP';
        observer.next(m);
      });
    });
  }

  observeSensor(area: Area, section: Section, position: string, sensor: string): Observable<Measurement> {
    console.log(`htlleonding/${area.name}/${section.name}/${position}/${sensor}/#`);
    let munit;
    switch (sensor) {
      case 'co2':
        munit = 'PPM';
        break;
      case 'light':
        munit = 'LUX';
        break;
      case 'humidity':
        munit = '%';
        break;
      case 'noise':
        munit = '';
        break;
      case 'db':
        munit = 'DB';
        break;
      case 'temperature':
        munit = '°C';
        break;
      case 'webcam':
        munit = 'IP';
        break;
      case 'window':
        munit = '';
        break;
      case 'luminosity':
        munit = 'LUX';
        break;
      default:
        throw new TypeError('failed to parse argument');
    }

    return new Observable<Measurement>(observer => {
      this.observe(`htlleonding/${area.name}/${section.name}/${position}/${sensor}/#`)
      .subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const m: Measurement = new Measurement();
        m.value = json.value;
        m.timestamp = json.timestamp;
        m.unit = munit;
        observer.next(m);
      });
    });
  }


  observeMqttRoom(): Observable<ModelAction> {
    return new Observable<ModelAction>(observer => {
      this.observe(`htlleodning/3dmodel/control/room`).subscribe((message: IMqttMessage) => {
        const json = JSON.parse(message.payload.toString());
        const action: ModelAction = new ModelAction();
        action.room = json.room;
        observer.next(action);
      });
    });
  }
}
