import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { WeatherService } from 'src/app/core/services/weather.service';
import { FormControl } from '@angular/forms';
import { Measurement } from 'src/app/shared/models';

@Component({
  selector: 'app-weather-overview',
  templateUrl: './weather-overview.component.html',
  styleUrls: ['./weather-overview.component.scss']
})
export class WeatherOverviewComponent implements OnInit, OnDestroy {

  /**
   *To save the interval reference and clear it later
   *
   * @type {*}
   * @memberof WeatherOverviewComponent
   */
  clockID: any;
  /**
   *Current dateTime thats updated by the clock intervall
   *
   * @memberof WeatherOverviewComponent
   */
  dateTime = new FormControl((new Date()).toISOString());

  /**
   *Input to collapse and expand the weather overview
   *Only shows header when collapsed
   *
   * @memberof WeatherOverviewComponent
   */
  @Input() headerOnly = false;

  /**
   *Display array of the measurements
   *
   * @type {Measurement[]}
   * @memberof WeatherOverviewComponent
   */
  measurements: Measurement[] = [];

  constructor(private weatherService: WeatherService) { }

  ngOnInit() {
    this.startClock();
    this.getSubscriptions();
  }

  click() {
  }

  getSubscriptions() {
    this.weatherService.observeOutdoorTemperature().subscribe(data => {
      let index = -1;
      this.measurements.forEach((m, i) => {
        if (m.type === data.type) {
          index = i;
        }
      });
      if (index >= 0) {
        this.measurements.splice(index, 1);
      }
      this.measurements.push(data);
    });
    this.weatherService.observeOutdoorHumidity().subscribe(data => {
      let index = -1;
      this.measurements.forEach((m, i) => {
        if (m.type === data.type) {
          index = i;
        }
      });
      if (index >= 0) {
        this.measurements.splice(index, 1);
      }
      this.measurements.push(data);
    });
    this.weatherService.observeOutdoorPressure().subscribe(data => {
      let index = -1;
      this.measurements.forEach((m, i) => {
        if (m.type === data.type) {
          index = i;
        }
      });
      if (index >= 0) {
        this.measurements.splice(index, 1);
      }
      this.measurements.push(data);
    });
    this.weatherService.observeOutdoorWindSpeed().subscribe(data => {
      let index = -1;
      this.measurements.forEach((m, i) => {
        if (m.type === data.type) {
          index = i;
        }
      });
      if (index >= 0) {
        this.measurements.splice(index, 1);
      }
      this.measurements.push(data);
    });
    this.weatherService.observeOutdoorWindDeg().subscribe(data => {
      let index = -1;
      this.measurements.forEach((m, i) => {
        if (m.type === data.type) {
          index = i;
        }
      });
      if (index >= 0) {
        this.measurements.splice(index, 1);
      }
      this.measurements.push(data);
    });
  }

  ngOnDestroy() {
    this.stopClock();
  }

  async startClock() {
    this.clockID = setInterval(() => {
      this.dateTime.setValue((new Date()).toISOString());
    }, 1000);
  }

  stopClock() {
    clearInterval(this.clockID);
  }
}
