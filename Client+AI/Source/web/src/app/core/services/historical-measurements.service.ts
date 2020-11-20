import { LanguageService } from './language.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {catchError, filter, map} from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Area, Section, MeasurementType, Measurement } from 'src/app/shared/models';
import {environment} from '../../../environments/environment';
import {error} from 'util';

const url = `${environment.vmUrl}${environment.corePathPrefix}`;

@Injectable()
export class HistoricalMeasurementService {

  /**
   * Creates an instance of HistoricalMeasurementService.
   * @param {HttpClient} http
   * @param {LanguageService} languageService
   * @memberof HistoricalMeasurementService
   */
  constructor(private http: HttpClient,
    private languageService: LanguageService) {
  }

  /**
   * format error message for console output
   * @param error error object
   */
  private formatErrors(error: any) {
    console.warn(error);
    return throwError(error.error);
  }

  /**
   * This method returns all areas
   *
   * @returns {Observable<Array<Area>>}
   * @memberof HistoricalMeasurementService
   */
  getAreas(): Observable<Array<Area>> {
    const language = this.languageService.getLanguage();
    return this.http.get<Array<Area>>(`${url}/area/all/${language.code}`)
      .pipe(
        map(json => {
          return this.toAreaArray(json);
        }),
        catchError(this.formatErrors)
      );
  }

  /**
   * This method returns all sections of an area
   *
   * @param {String} area
   * @returns {Observable<Array<Section>>}
   * @memberof HistoricalMeasurementService
   */
  getSections(area: String): Observable<Array<Section>> {
    const language = this.languageService.getLanguage();
    return this.http.get<Array<Section>>(`${url}/section/${area}/${language.code}`)
      .pipe(
        map(json => {
          return this.toSectionArray(json);
        }),
        catchError(this.formatErrors)
      );
  }

  /**
   * This method returns all types that are measured of a room
   *
   * @param {String} area
   * @param {String} section
   * @param {String} position
   * @returns {Observable<Array<MeasurementType>>}
   * @memberof HistoricalMeasurementService
   */
  getMeasurementTypesOfRoom(area: String, section: String, position: String): Observable<Array<MeasurementType>> {
    const language = this.languageService.getLanguage();
    return this.http.get<Array<MeasurementType>>(`${url}/sensortype/position/${area}/${section}/${position}/${language.code}`)
      .pipe(
        map(json => {
          return this.toMeasurementTypeArray(json);
        }),
        catchError(this.formatErrors)
      );
  }


  filterOutNull(json: any) {
    return json.sensortypesforsection !== null;
  }

  /**
   * This method returns all sensors which are located at specific section
   *
   * @param {String} area
   * @param {string} section
   * @returns {Observable<Section>}
   * @memberof HistoricalMeasurementService
   */
  getSensorsOfSection(area: String, section: string): Observable<Section> {
    const language = this.languageService.getLanguage();
    return this.http.get<Section>(`${url}/sensortype/section/${area}/${section}/${language.code}`)
      .pipe(
        filter(json => this.filterOutNull(json)),
        map(json => {
          return this.toSensorsOfSection(json, section);
        }),
        catchError(this.formatErrors)
      );
  }

  // todo map unit kommt noch vom server hinzu !!!
  /**
   * This method returns an average value of a sensor located at a section for each day between a specified time period
   *
   * @param {String} area
   * @param {String} section
   * @param {String} sensortype
   * @param {String} from
   * @param {String} to
   * @returns {Observable<Array<Measurement>>}
   * @memberof HistoricalMeasurementService
   */
  getDailyAvgOfSensorFromPeriod(area: String, section: String, sensortype: String, from: String, to: String)
    : Observable<Array<Measurement>> {

    const language = this.languageService.getLanguage();
    return this.http
      .get<Array<Measurement>>(`${url}/measurement/average/range/day/${area}/${section}/${sensortype}/${from}/${to}/${language.code}`)
      .pipe(
        map(json => {
          console.log(json);
          return this.toDailyAvgArray(json);
        }),
        catchError(this.formatErrors)
      );
  }

  // todo map unit kommt noch vom server hinzu !!!
  /**
   * This method returns for each hour an average value of a sensortype of a specified date at a section
   *
   * @param {String} area
   * @param {String} section
   * @param {String} sensortype
   * @param {String} date
   * @returns {Observable<Array<Measurement>>}
   * @memberof HistoricalMeasurementService
   */
  getAllDailyHourValues(area: String, section: String, sensortype: String, date: String): Observable<Array<Measurement>> {
    const language = this.languageService.getLanguage();
    return this.http.get<Array<Measurement>>(`${url}/measurement/average/hour/${area}/${section}/${sensortype}/${date}/${language.code}`)
      .pipe(
        map(json => {
          return this.toAllDailyHourArray(json);
        }),
        catchError(this.formatErrors)
      );
  }

  getHourlyAveragesFromTimespan(area: String, section: String, type: String, from: String, to: String): Observable<Array<Measurement>> {
    const language = this.languageService.getLanguage();
    console.log(from, to);
    return this.http.get<Array<Measurement>>
      (`${url}/measurement/average/range/hour/${area}/${section}/${type}/${from}/${to}/${language.code}`)
      .pipe(
        map(json => {
          console.log(json);
          return this.toHourlyAveragesFromTimespanArray(json);
        }),
        catchError(this.formatErrors)
      );
  }

  // todo map unit kommt noch vom server hinzu !!!
  /**
   * This method returns one av erage value of a sensortype from a specified section between a time period
   *
   * @param {String} area
   * @param {String} section
   * @param {String} sensortype
   * @param {String} from
   * @param {String} to
   * @returns {Observable<Measurement>}
   * @memberof HistoricalMeasurementService
   */
  getAverageValueOfSensortypeBetweenDate(area: String, section: String, sensortype: String, from: String, to: String)
    : Observable<Measurement> {

    const language = this.languageService.getLanguage();
    return this.http.get<Measurement>(`${url}/measurement/average/all/${area}/${section}/${sensortype}/${from}/${to}/${language.code}`)
      .pipe(
        map((json: any) => {
          const measurement: Measurement = new Measurement();
          measurement.value = json.average;
          measurement.unit = json.unit;
          return measurement;
        }),
        catchError(this.formatErrors)
      );
  }

  // todo map unit kommt noch vom server hinzu !!!
  /**
   * This method returns an average value for each month of a sensortype at a specified section between a time period
   *
   * @param {String} area
   * @param {String} section
   * @param {String} sensortype
   * @param {String} year
   * @param {String} from
   * @param {String} to
   * @returns {Observable<Array<Measurement>>}
   * @memberof HistoricalMeasurementService
   */
  getMonthlyAvgValueFromPeriod(area: String, section: String, sensortype: String, year: String,
    from: String, to: String): Observable<Array<Measurement>> {

    const language = this.languageService.getLanguage();
    return this.http.get<Array<Measurement>>(
      `${url}/measurement/average/month/${area}/${section}/${sensortype}/${year}/${from}/${to}/${language.code}`)
      .pipe(
        map(json => {
          return this.toMonthlyAvgArray(json);
        }),
        catchError(this.formatErrors)
      );
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @returns {Section[]}
   * @memberof HistoricalMeasurementService
   */
  toSectionArray(json: any): Section[] {
    const sections = new Array<Section>();
    json.sections.forEach(s => {
      const section = new Section();
      section.name = s;
      sections.push(section);
    });
    return sections;
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @returns {MeasurementType[]}
   * @memberof HistoricalMeasurementService
   */
  toMeasurementTypeArray(json: any): MeasurementType[] {
    const measurementTypes = new Array<MeasurementType>();
    if (json) {
      json.sensorTypes.forEach(m => {
        const measurementType = new MeasurementType();
        measurementType.name = m;
        measurementTypes.push(measurementType);
      });
    }
    return measurementTypes;
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @returns {Area[]}
   * @memberof HistoricalMeasurementService
   */
  toAreaArray(json: any): Area[] {
    const array = new Array<Area>();
    json.forEach(a => {
      const area = new Area();
      area.name = a.key;
      area.displayName = a.value;
      array.push(area);
    });
    return array;
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @param {string} sectionName
   * @returns {Section}
   * @memberof HistoricalMeasurementService
   */
  toSensorsOfSection(json: any, sectionName: string): Section {
    const section = new Section();
    section.name = sectionName;
    section.sensors = [];
    json.sensortypesforsection.forEach((sensor: any) => {
      const position = sensor.position;
      sensor.sensortypes.forEach((type: any) => {
        const sensorType = new MeasurementType();
        sensorType.name = type;
        sensorType.position = position;
        section.sensors.push(sensorType);
      });
    });
    return section;
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @returns {Measurement[]}
   * @memberof HistoricalMeasurementService
   */
  toDailyAvgArray(json: any): Measurement[] {
    const array = new Array<Measurement>();
    json.averagefordays.forEach(a => {
      const measurement = new Measurement();
      measurement.value = Math.round(a.value * 100) / 100;
      measurement.timestamp = a.date;
      measurement.unit = a.unit;
      array.push(measurement);
    });
    return array;
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @returns {Measurement[]}
   * @memberof HistoricalMeasurementService
   */
  toAllDailyHourArray(json: any): Measurement[] {
    const array = new Array<Measurement>();
    json.averageforhourperday.forEach(a => {
      const measurement = new Measurement();
      measurement.value = Math.round(a.value * 100) / 100;
      measurement.hour = `${a.hour < 10 ? '0' : ''}${a.hour}:00`;
      measurement.unit = a.unit;
      array.push(measurement);
    });
    return array;
  }

  /**
   *Converts the json response to an array
   *
   * @param {*} json
   * @returns {Measurement[]}
   * @memberof HistoricalMeasurementService
   */
  toMonthlyAvgArray(json: any): Measurement[] {
    const array = new Array<Measurement>();
    json.averageformonthperyear.forEach(a => {
      const measurement = new Measurement();
      measurement.value = Math.round(a.value * 100) / 100;
      measurement.month = a.month;
      measurement.unit = a.unit;
      array.push(measurement);
    });
    return array;
  }

  /**
   *Converts the json response to an array
   *
   * @param {Measurement[]} json
   * @returns {*}
   * @memberof HistoricalMeasurementService
   */
  toHourlyAveragesFromTimespanArray(json: any): Measurement[] {
    const array = new Array<Measurement>();

    json.forEach(m => {
      const measurement = new Measurement();
      measurement.hour = m.hour;
      measurement.value = m.value;
      measurement.unit = m.unit;
      const date = new Date(m.timestamp);
      date.setHours(m.hour);
      measurement.timestamp = date;
      array.push(m);
    });

    return array;
  }

  /*toTypeArray(json: any): MeasurementType[] {
    const types = new Array<MeasurementType>();
    json.types.forEach(t => {
      types.push(t);
    });
    return types;
  }*/

  /*getTimespan(roomId: String, type: String, timespan: any): Observable<Measurement[]> {
    return this.http.post<Measurement[]>(`${url }/room/${roomId}/measurement/${type}/timespan`, {
      from: timespan.from,
      to: timespan.to,
      interval: timespan.interval // make anon obj to class later
    }).pipe(
      map(json => {
        return this.toMeasurementArray(json);
      })
    );
  }*/
}
