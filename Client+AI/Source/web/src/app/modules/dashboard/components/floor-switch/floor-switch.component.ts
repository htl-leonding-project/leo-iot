import { Component, OnInit, Output, EventEmitter, Input, SimpleChanges, OnChanges } from '@angular/core';
import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { Area } from 'src/app/shared/models';

@Component({
  selector: 'app-floor-switch',
  templateUrl: './floor-switch.component.html',
  styleUrls: ['./floor-switch.component.scss']
})
export class FloorSwitchComponent implements OnInit, OnChanges {

  /**
   *Containing the name of the active floor
   *
   * @memberof FloorSwitchComponent
   */
  active: string;

  /**
   *The currently displayed/active floor
   *
   * @type {Area}
   * @memberof FloorSwitchComponent
   */
  @Input() displayArea: Area;

  /**
   *For Emitting selected areas
   *
   * @type {EventEmitter<Area>}
   * @memberof FloorSwitchComponent
   */
  @Output() floorSwitch: EventEmitter<Area> = new EventEmitter<Area>();

  /**
   *Creates an instance of FloorSwitchComponent.
   * @param {HistoricalMeasurementService} layoutService
   * @memberof FloorSwitchComponent
   */
  constructor(private layoutService: HistoricalMeasurementService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.displayArea) {
      this.displayArea = changes.displayArea.currentValue;
      this.active = this.displayArea.name;
    }
  }

  switchFloor(name: string) {
    this.active = name;
    const area = new Area();
    area.name = name;
    this.floorSwitch.emit(area);
    /*const newArea = await this.layoutService.getAreas('en').toPromise().then(areas => {
      return areas.find(area => area.name === name);
    });
    if (newArea) {
      console.log(newArea);
      this.floorSwitch.emit(newArea);
    }*/
  }
}
