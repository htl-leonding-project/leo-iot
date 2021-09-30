import {Measurement} from '../../../shared/models';
import {MeasurementTypeAndValue} from '../../../modules/dashboard/components';

export class RoomDataHolder {
  public lastMeasurements: Map<String, Measurement> = new Map<String, Measurement>();
  public temperature: MeasurementTypeAndValue = this.data.find((elem) => elem.type === 'temperature');
  public humidity: MeasurementTypeAndValue = this.data.find((elem) => elem.type === 'humidity');
  public co2: MeasurementTypeAndValue = this.data.find((elem) => elem.type === 'co2');
  public light: MeasurementTypeAndValue = this.data.find((elem) => elem.type === 'light');
  public volume: MeasurementTypeAndValue = this.data.find((elem) => elem.type === 'db');

  constructor(public room: string, public data = new Array<MeasurementTypeAndValue>()) {
    data.forEach(value => {
      value.measurement.subscribe(val =>
        this.lastMeasurements.set(value.type, val)
      );
    });
  }

  hasWebcam(): boolean {
    return this.lastMeasurements.has('webcam') &&
      this.lastMeasurements.get('webcam').value !== undefined;
  }

  getWebcamIp(): string {
    if (!this.hasWebcam()) {
      return '';
    }
    return this.lastMeasurements.get('webcam').value;
  }

  getSensor(sensorType: string) {
    if (sensorType === 'volume') {
      sensorType = 'db';
    }
    return this.data.find(elem => elem.type === sensorType);
  }

  getLastMeasurement(sensorType: string) {
    if (sensorType === 'volume') {
      sensorType = 'db';
    }
    if (this.lastMeasurements.has(sensorType)) {
      return this.lastMeasurements.get(sensorType);
    }
    return undefined;
  }

  getSensorMeasurement(sensorType: string) {
    const sensor = this.getSensor(sensorType);
    if (sensor !== undefined) {
      return sensor.measurement;
    }
    return undefined;
  }

  hasNoSensor(): boolean {
    return this.data === undefined || this.data.length === 0;
  }

  getDisplayData(): Array<MeasurementTypeAndValue> {
    return this.data.filter(value => value.type !== 'webcam');
  }
}



