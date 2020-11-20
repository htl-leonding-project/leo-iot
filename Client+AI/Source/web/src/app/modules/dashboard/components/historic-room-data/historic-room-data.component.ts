import { Component, OnInit, ViewChild, Input, OnChanges, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource, MatInput, MatButtonToggleGroup, MatTabGroup } from '@angular/material';

import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { MeasurementType, Measurement, Area, Section } from 'src/app/shared/models';

import { Chart } from 'chart.js';
import 'chartjs-plugin-zoom';
import { Router } from '@angular/router';

/**
 *This component is massive, massively broken, dont change it if you dont absolutely need to
 *In hindsight i would do all the tabs in their own components but now im commited so fuck it
 *If you have to edit this, gl m8.
 *
 *I tried to keep the chart and table somewhat clean but the filter part is an utter mess, im sry
 *
 * @export
 * @class HistoricRoomDataComponent
 * @implements {OnInit}
 * @implements {OnChanges}
 */
@Component({
  selector: 'app-historic-room-data',
  templateUrl: './historic-room-data.component.html',
  styleUrls: ['./historic-room-data.component.scss'],
})
export class HistoricRoomDataComponent implements OnInit, OnChanges {

  /**
   *Currently selected MeasurementType
   *
   * @type {MeasurementType}
   * @memberof HistoricalMeasurementsComponent
   */
  @Input() displayType: MeasurementType = new MeasurementType();

  /**
   *Currently selected Area
   *
   * @type {String}
   * @memberof HistoricalMeasurementsComponent
   */
  @Input() displayArea: Area;

  /**
   *Currently selected Section
   *
   * @type {String}
   * @memberof HistoricalMeasurementsComponent
   */
  @Input() displaySection: Section;

  /**
   *Event that emits when user want to navigate back to room-measurements component
   *
   * @type {EventEmitter<void>}
   * @memberof HistoricRoomDataComponent
   */
  @Output() navigateBack: EventEmitter<void> = new EventEmitter<void>();

  /**
   *reference to the period selection buttons
   *
   * @type {MatButtonToggleGroup}
   * @memberof HistoricRoomDataComponent
   */
  @ViewChild(MatButtonToggleGroup, {static: false}) displayPeriod: MatButtonToggleGroup;

  /**
   *Binding the maximum date picker value
   *
   * @memberof HistoricalMeasurementsComponent
   */
  pickerMax = new Date();

  /**
   *Binding the to_picker minimum value
   *
   * @memberof HistoricalMeasurementsComponent
   */
  toPickerMin;

  /**
   *Binding the to_picker disabled state
   *
   * @memberof HistoricalMeasurementsComponent
   */
  toPickerDisabled = true;

  /**
   *Binding the show touchUI property states
   *
   * @memberof HistoricalMeasurementsComponent
   */
  showTouchUI = false;

  intervalType = 'daily';

  /**
   *fromPickerInput reference
   *
   * @type {MatInput}
   * @memberof HistoricalMeasurementsComponent
   */
  @ViewChild('fromPickerInput', {static: false, read: MatInput }) fromPickerInput: MatInput;

  /**
   *toPickerInput reference
   *
   * @type {MatInput}
   * @memberof HistoricalMeasurementsComponent
   */
  @ViewChild('toPickerInput', {static: false, read: MatInput }) toPickerInput: MatInput;

  /**
   *fromPicker reference
   *
   * @memberof HistoricalMeasurementsComponent
   */
  @ViewChild('fromPicker', {static: false}) fromPicker;

  /**
   *toPicker reference
   *
   * @memberof HistoricalMeasurementsComponent
   */
  @ViewChild('toPicker',{static: false}) toPicker;

  /**
   *displayed column definitions for mat table (order is interpreted)
   *
   * @type {string[]}
   * @memberof HistoricalMeasurementsComponent
   */
  displayedColumns: string[] = ['value', 'unit', 'timestamp'];

  /**
   *mat table data source changable with `dataSource.data`
   *
   * @memberof HistoricalMeasurementsComponent
   */
  dataSource = new MatTableDataSource<Measurement>();

  /**
   *Binding the loading spinner state
   *
   * @memberof HistoricalMeasurementsComponent
   */
  isLoadingResults = true;

  /**
   *mat paginator reference
   *
   * @type {MatPaginator}
   * @memberof HistoricalMeasurementsComponent
   */
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  /**
   *mat sort reference
   *
   * @type {MatSort}
   * @memberof HistoricalMeasurementsComponent
   */
  @ViewChild(MatSort,{static: false}) sort: MatSort;

  /**
   *tab group reference
   *
   * @type {MatTabGroup}
   * @memberof HistoricRoomDataComponent
   */
  @ViewChild(MatTabGroup,{static: false}) tabs: MatTabGroup;

  /**
   *chart.js chart object
   *
   * @type {Chart}
   * @memberof HistoricRoomDataComponent
   */
  chart: Chart;
  labels;
  dataset;
  plugins = {
    pan: {
      enabled: true,
      mode: 'xy'
    },
    zoom: {
      enabled: true,
      mode: 'xy',
      limits: {
        max: 10,
        min: 0.5
      }
    },
  };

  /**
   *Creates an instance of HistoricalMeasurementsComponent.
   * @memberof HistoricalMeasurementsComponent
   */
  constructor(private measurementsService: HistoricalMeasurementService, private router: Router) { }

  ngOnInit() {
    this.applyFilters().then(_ => {
      this.initChart();
    });
    this.tabs.selectedIndexChange.subscribe(index => {
      if (index === 0) {
        this.back();
      }
    });
  }

  /**
   *dynamically updating measurement type
   *
   * @param {SimpleChanges} changes
   * @memberof HistoricalMeasurementsComponent
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes.displayType) {
      this.displayType = changes.displayType.currentValue;
      if (changes.displayType.currentValue) {
        this.applyFilters();
      }
    }
  }

  /**
   *managing switches between the 2 tabs
   *
   * @param {*} event
   * @memberof HistoricalMeasurementsComponent
   */
  tabSwitch(event: any) {
    if (event.index === 1) {
      this.initChart();
    } else if (event.index === 2) {
      this.initTable();
    }
  }

  /**
   *Initializes the table
   *
   * @memberof HistoricalMeasurementsComponent
   */
  initTable() {
    this.dataSource.paginator = this.paginator;
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
  }

  /**
   *Initializes the chart and binding it to the `measurement-chart` element
   *
   * @memberof HistoricalMeasurementsComponent
   */
  initChart() {
    this.chart = new Chart('measurement-chart', {
      type: 'line',
      data: {
        labels: this.labels,
        datasets: [
          {
            data: this.dataset,
            label: this.displayType.name,
            borderColor: '#F57C00',
            borderWidth: 1,
            backgroundColor: '#ffffff33',
            fill: true,
            lineTension: 0.4,
            pointBackgroundColor: '#F57C00'
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        responsiveAnimationDuration: 1000,
        legend: {
          display: false
        },
        elements: {
          point: {
            radius: 0,
            hitRadius: 10,
            hoverRadius: 5,
          }
        },
        scales: {
          yAxes: [{
            ticks: {
              suggestedMin: 0,
              suggestedMax: 50,
              fontColor: '#fff'
            },
            gridLines: {
              color: '#ffffff'
            },
          }],
          xAxes: [{
            gridLines: {
              display: false,
              color: '#ffffff'
            },
          }]
        },
        plugins: {
          zoom: {
            enabled: true,
            mode: 'xy',
          }
        }
      },
    });
  }

  /**
   *re-init the chart with new values, also triggers the build up animation
   *
   * @memberof HistoricRoomDataComponent
   */
  updateChart() {
    this.labels = [];
    this.dataset = [];
    this.dataSource.data.forEach(d => {
      if (d.timestamp) {
        if (this.displayPeriod.value === 'day') {
          this.labels.push(new Date(d.timestamp).toLocaleTimeString());
        } else {
          this.labels.push(new Date(d.timestamp).toLocaleDateString());
        }
      } else {
        this.labels.push(d.hour);
      }

      this.dataset.push(d.value);
    });

    if (this.chart) {
      this.chart.data.labels = this.labels;
      this.chart.data.datasets[0].data = this.dataset;
      this.chart.update();
    }
  }

  /**
   *Fetching data, applying set filters and managing loading states
   *
   *Fetching data is a bit complicated because the rest api only has one implementation
   *to get historical measurements between two dates. So we need some helper functions to get
   *measurements for current month and year
   * @memberof HistoricalMeasurementsComponent
   */
  async applyFilters() {
    if (!this.displayArea || !this.displaySection || !this.displayType) { return; }
    this.isLoadingResults = true;

    if (this.fromPicker.startAt && this.toPicker.startAt) {
      this.isLoadingResults = true;
      const timespan = {
        from: this.fromPicker.startAt.toDateString(),
        to: this.toPicker.startAt.toDateString()
      };

      if (this.intervalType === 'daily') {
        await this.measurementsService.getDailyAvgOfSensorFromPeriod(
          this.displayArea.name.toLowerCase(),
          this.displaySection.name.toLowerCase(),
          this.displayType.name.toLowerCase(),
          timespan.from,
          timespan.to,
        ).subscribe(data => {
          this.dataSource.data = data;
          this.updateChart();
          this.updateDatePickers();
        });
      } else if (this.intervalType === 'hourly') {
        await this.measurementsService.getHourlyAveragesFromTimespan(
          this.displayArea.name.toLowerCase(),
          this.displaySection.name.toLowerCase(),
          this.displayType.name.toLowerCase(),
          timespan.from,
          timespan.to
        ).subscribe(data => {
          console.log(data);
          this.dataSource.data = data;
          this.updateChart();
          this.updateDatePickers();
        });
      }

      this.displayPeriod.value = '';
      this.isLoadingResults = false;
    } else if (this.displayPeriod) {
      switch (this.displayPeriod.value) {
        case 'day':
          this.dataSource.data = await this.getMeasurementsDay();
          this.isLoadingResults = false;
          this.updateChart();
          break;
        case 'month':
          this.dataSource.data = await this.getMeasurementsMonth();
          this.isLoadingResults = false;
          this.updateChart();
          break;
        case 'year':
          this.dataSource.data = await this.getMeasurementsYear();
          this.isLoadingResults = false;
          this.updateChart();
          break;
        default:
          this.dataSource.data = await this.getMeasurementsDay();
          this.isLoadingResults = false;
          this.updateChart();
          break;
      }
    } else {
      this.dataSource.data = await this.getMeasurementsDay();
      this.isLoadingResults = false;
      this.updateChart();
    }
    this.resetFilters();
  }

  /**
   *Resetting all filters
   *
   * @memberof HistoricalMeasurementsComponent
   */
  resetFilters() {
    this.toPickerDisabled = true;
    this.toPickerMin = null;
    this.pickerMax = new Date();
  }

  /**
   *Changes the toPicker minimum value after the fromPicker value has been selected
   *also removes the display period value
   *
   * @param {*} event
   * @memberof HistoricalMeasurementsComponent
   */
  changeToPickerMin(event: any) {
    this.toPickerMin = event.value;
  }

  /**
   *Resets the date picker inputs
   *
   * @memberof HistoricalMeasurementsComponent
   */
  updateDatePickers() {
    this.toPickerInput.value = undefined;
    this.fromPickerInput.value = undefined;
  }

  /**
   *Helper function to get all measurements from the current day as hourly avgs
   * > I know these should be in the servcie but whatever
   * @returns Measurements[]
   * @memberof HistoricalMeasurementsComponent
   */
  async getMeasurementsDay() {
    let result = [];
    await this.measurementsService.getAllDailyHourValues(
      this.displayArea.name.toLowerCase(),
      this.displaySection.name.toLowerCase(),
      this.displayType.name.toLowerCase(),
      new Date().toDateString()).toPromise().then(data => {
        result = data;
      });
    return result;
  }

  /**
   *Helper function to get all measurements from the current month as daily avgs
   *
   * @returns Measurements[]
   * @memberof HistoricalMeasurementsComponent
   */
  async getMeasurementsMonth() {
    const from = new Date(new Date().setDate(0));
    const to = new Date();
    let result = [];
    await this.measurementsService.getDailyAvgOfSensorFromPeriod(
      this.displayArea.name.toLowerCase(),
      this.displaySection.name.toLowerCase(),
      this.displayType.name.toLowerCase(),
      from.toDateString(),
      to.toDateString())
      .toPromise().then(measurements => {
        result = measurements;
      });
    console.log(result);
    return result;
  }

  /**
 *Helper function to get all measurements from the current year as daily avgs
 *
 * @returns Measurements[]
 * @memberof HistoricalMeasurementsComponent
 */
  async getMeasurementsYear() {
    const from = new Date(new Date().setMonth(0, 0));
    const to = new Date();
    let result = [];
    await this.measurementsService.getDailyAvgOfSensorFromPeriod(
      this.displayArea.name.toLowerCase(),
      this.displaySection.name.toLowerCase(),
      this.displayType.name.toLowerCase(),
      from.toDateString(),
      to.toDateString())
      .toPromise().then(measurements => {
        result = measurements;
      });
    console.log(result);
    return result;
  }

  /**
   *Changes the tab groups active tab to chart (index 1) after submited filter
   *
   * @memberof HistoricRoomDataComponent
   */
  changeToChart() {
    this.tabs.selectedIndex = 1;
  }

  /**
   *Navigates back to the sections RoomMeasurementComponent
   *
   * @memberof HistoricRoomDataComponent
   */
  back() {
    this.router.navigate(['dashboard', this.displayArea.name.toLocaleLowerCase(), this.displaySection.name.toLocaleLowerCase()]);
    this.navigateBack.emit(null);
  }
}
