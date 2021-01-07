import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Trip } from 'src/app/shared/models';
import { of, Observable } from 'rxjs';

/**
 * documentation:
 * http://data.linz.gv.at/katalog/linz_ag/linz_ag_linien/fahrplan/LINZ_LINIEN_Schnittstelle_EFA_V1.pdf
 */
@Injectable()
export class TramService {

  /**
   * Creates an instance of TramService.
   * @param {HttpClient} http
   * @memberof TramService
   */
  constructor(private http: HttpClient) { }

  /**
   *observable of the last trip
   *
   * @type {Observable<Trip>}
   * @memberof TramService
   */
  lastTrip: Observable<Trip> = null;

  /**
   * this method get's the next departure
   *
   * @returns
   * @memberof TramService
   */
  getNextDeparture() {
    if (this.lastTrip) {
      return this.lastTrip;
    }
    return this.http.get<Trip>(urls.url).pipe(
      map((json: any) => {
        const t: Trip = new Trip();
        t.depName = json.trips[1].legs[0].points[0].name;
        t.arrName = json.trips[1].legs[0].points[1].name;
        t.depTime = json.trips[1].legs[0].points[0].dateTime.time;
        t.arrTime = json.trips[1].legs[0].points[1].dateTime.time;
        // tslint:disable-next-line:radix
        const arrDate = new Date(0, 0, 0, parseInt(t.arrTime.substr(0, 2)), parseInt(t.arrTime.substr(3)), 0, 0);
        // tslint:disable-next-line:radix
        const depDate = new Date(0, 0, 0, parseInt(t.depTime.substr(0, 2)), parseInt(t.depTime.substr(3)), 0, 0);
        t.duration = new Date(arrDate.getTime() - depDate.getTime()).getMinutes();
        // tslint:disable-next-line:radix
        t.until = parseInt(t.depTime.substr(3)) - (new Date()).getMinutes();

        if (t.until > 10 || t.until < 1) {
          t.until = 10;
        }
        this.lastTrip = of(t);
        return t;
      })
    );
  }
}

/**
 * linz agi api urls
 */
export const urls = {
  // because of cors we moved the api call to a small heroku app
  url: 'https://iot-helper-service.herokuapp.com/tram',
  // parameters like date are not up to date ofc
  // tslint:disable-next-line:max-line-length
  withParameters: 'https://www.linzag.at/static/XML_TRIP_REQUEST2?outputFormat=json&locationServerActive=1&type_origin=stopID&name_origin=60500120&type_destination=stopID&name_destination=60501720&itdTripDateTimeDepArr=dep&itdDate=20181205&itdTime=1827&excludedMeans=1&excludedMeans=2&excludedMeans=3&excludedMeans=5&excludedMeans=6&excludedMeans=7&excludedMeans=8&excludedMeans=9&excludedMeans=10&excludedMeans=11&excludedMeans=12&excludedMeans=13&excludedMeans=14&excludedMeans=15&excludedMeans=16&excludedMeans=17&excludedMeans=18&excludedMeans=19&limit=1'
};
