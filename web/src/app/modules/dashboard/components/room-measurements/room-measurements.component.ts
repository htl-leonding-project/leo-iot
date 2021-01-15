import { Component, OnInit, ViewChildren, QueryList, EventEmitter, OnChanges, SimpleChanges, Input, Output } from '@angular/core';
import { MatRipple } from '@angular/material';
import { Observable, of } from 'rxjs';

import { AutoplayService } from 'src/app/core/services/autoplay.service';
import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { LiveMeasurementService } from 'src/app/core/services/live-measurements.service';
import { Section, Area, MeasurementType, Measurement } from 'src/app/shared/models';


import Glide, {
  Autoplay,
  Controls,
  Swipe
} from '@glidejs/glide/dist/glide.modular.esm';



@Component({
  selector: 'app-room-measurements',
  templateUrl: './room-measurements.component.html',
  styleUrls: ['./room-measurements.component.scss']
})
export class RoomMeasurementsComponent implements OnChanges {

  /**
   *Emits the selected measurement type
   *
   * @type {EventEmitter<MeasurementType>}
   * @memberof RoomMeasurementsComponent
   */
  @Output() typeSelected: EventEmitter<MeasurementType> = new EventEmitter<MeasurementType>();

  /**
   *The currently selected room
   *
   * @type {Section}
   * @memberof RoomMeasurementsComponent
   */
  @Input() displaySection: Section = null;

  /**
   *The currently selected floor
   *
   * @type {Area}
   * @memberof RoomMeasurementsComponent
   */
  @Input() displayArea: Area = null;

  /**
   *the final array, 4 items grouped
   *
   * @type {Array<MeasurementTypeAndValue>}
   * @memberof RoomMeasurementsComponent
   */
  displayArray: Array<MeasurementTypeAndValue> = [];

  /**
   *List of all ripple directives in the markup to trigger them on click later
   *
   * @type {QueryList<MatRipple>}
   * @memberof RoomMeasurementsComponent
   */
  @ViewChildren(MatRipple) ripples !: QueryList<MatRipple>;

  /**
   *Creates an instance of RoomMeasurementsComponent.
   * @param {AutoplayService} autoplayService
   * @param {HistoricalMeasurementService} measurementService
   * @param {LiveMeasurementService} liveService
   * @memberof RoomMeasurementsComponent
   */
  constructor(
    private autoplayService: AutoplayService,
    private measurementService: HistoricalMeasurementService,
    private liveService: LiveMeasurementService
  ) { }

  glide;

  /**
   *Initializes the carousel and sets the autoplay time
   *
   * @memberof RoomMeasurementsComponent
   */
  initGlide() {
    // get the current autoplay settings from the service
    return this.autoplayService._autoplay.subscribe(autoplay => {
      if (this.displaySection) {
        setTimeout(() => {
          this.glide = new Glide('.glide', { autoplay: autoplay.state ? autoplay.interval : autoplay.state, type: 'carousel' })
            .mount({ Autoplay, Controls, Swipe });
        }, 0);
        /*this.glide.on('mount.after', function () {
          this.glide.go('>');
        });*/
      }
    });
  }

  /**
   *Listens for changes to the displayed measurement type
   *
   * @param {SimpleChanges} changes
   * @memberof RoomMeasurementsComponent
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes.displayArea) {
      this.displayArea = changes.displayArea.currentValue;
    }
    if (changes.displaySection) {
      console.log(changes.displaySection);
      this.displaySection = changes.displaySection.currentValue;
    }
    this.getMeasurementTypesOfRoom().then(status => {
      if (status) {
        this.initGlide();
      }
    });
  }

  /**
   *Fetching all measurementTypes of the room with the live values
   *Returns a promise that resolves in true or false
   *
   * @param {Section} room
   * @memberof RoomMeasurementsComponent
   */
  async getMeasurementTypesOfRoom() {
    const a = this.displayArea;
    const s = this.displaySection;

    if (!a || !s) { return of(false).toPromise(); }

    const sectionWithSensors = await this.measurementService.getSensorsOfSection(a.name, s.name).toPromise();
    console.log(sectionWithSensors);

    const positions = [];
    positions.push(sectionWithSensors.sensors[0].position);
    await sectionWithSensors.sensors.forEach(sensor => {
      if (positions.includes(!sensor.position)) {
        positions.push(sensor.position);
      }
    });

    const sensorTypeAndMeasurementsArray = [];
    return of(sectionWithSensors.sensors.map(async sensor => {
      try {
        let measurement = (await this.liveService.observeSensor(a, s, positions[0], sensor.name));
        if (measurement === null){
          return false;
        }
        sensorTypeAndMeasurementsArray.push({
          type: sensor.name, measurement: measurement
        });
      } catch (e) {
        return false;
      }
    })).toPromise().then(() => {
      this.groupSensorMeasurements(sensorTypeAndMeasurementsArray);
      return true;
    });
  }

  /**
   *Grouping all sensorTypes by arrays of length 4
   *
   * @memberof RoomMeasurementsComponent
   */
  groupSensorMeasurements(array: any) {
    this.displayArray = null;
    const arr = [];
    for (let index = 0; index < array.length; index += 4) {
      arr.push(array.slice(index, index + 4));
    }
    this.displayArray = arr;
  }

  /**
   *To react to users clicking on measurementTypes
   *
   * @param {number} indexInGroup the index of the item in range of 0 to 3 (in the by 4 grouped array)
   * @param {number} indexOfGroup the index of the by 4 grouped array
   * @memberof RoomMeasurementsComponent
   */
  selectMeasurement(indexInGroup: number, indexOfGroup: number) {
    console.log(indexInGroup, indexOfGroup);
    const index = indexInGroup + indexOfGroup * 4;
    this.launchRipple(index);
    const type = new MeasurementType();
    type.name = this.displayArray[indexOfGroup][indexInGroup].type;
    this.typeSelected.emit(type);
  }

  /**
   *Shows a ripple effect on the clicked element that persists
   *More info here https://material.angular.io/components/ripple/overview#manual-ripples
   *
   * @memberof RoomMeasurementsComponent
   */
  launchRipple(index: number) {
    this.ripples.forEach(ripple => ripple.fadeOutAll());
    this.ripples.forEach((ripple, i) => {
      if (i === index) {
        ripple.launch({
          color: '55555555',
          persistent: true
        });
      }
    });
  }
}

export interface MeasurementTypeAndValue {
  /**
   *measurement type display text
   *
   * @type {string}
   * @memberof MeasurementTypeAndValue
   */
  type: string;

  /**
   *observable measurement to unwrap in view and display live data
   *
   * @type {Observable<Measurement>}
   * @memberof MeasurementTypeAndValue
   */
  measurement: Observable<Measurement>;
}
