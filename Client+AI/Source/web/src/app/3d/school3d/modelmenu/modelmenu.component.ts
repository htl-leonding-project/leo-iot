import {Component, Input, OnInit} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {ModelController} from '../ModelController/modelController';
import {MeasurementTypeAndValue} from '../../../modules/dashboard/components';
import {RoomDataHolder} from './roomDataHolder';


@Component({
  selector: 'app-modelmenu',
  templateUrl: './modelmenu.component.html',
  styleUrls: ['./modelmenu.component.scss'],
  animations: [
    trigger('slideInOut', [
      state('open', style({
        height: '100%',
      })),
      state('closed', style({
        height: '0%',
      })),
      transition('open => closed', animate('400ms ease-in-out')),
      transition('closed => open', animate('400ms ease-in-out'))
    ])
  ]

})
export class ModelmenuComponent {

  static currentFilter = 'basic';
  @Input() menuOpened: boolean ;
  @Input() currentRoom: RoomDataHolder;
  @Input() selectedFloor: string;
  @Input() applyFilter;
  @Input() floorSelect;


  constructor() {
  }

  animationState() {
    return this.menuOpened ? 'open' : 'closed';
  }


  openMenuEvent() {
    this.menuOpened = true;
  }

  closeMenuEvent() {
    this.menuOpened = false;
  }

  floorSelectEvent(cellar: string) {
    this.floorSelect(cellar);
  }

  getCurrentRoom() {
    return this.currentRoom.room;
  }

  floorBackgroundColor(floor: string) {
    return floor === this.selectedFloor ? 'white' : '';
  }

  floorColor(floor: string) {
    return floor === this.selectedFloor ? 'gray' : 'white';
  }

  getData(): Array<MeasurementTypeAndValue> {
    if (this.currentRoom.hasNoSensor()) {
      return new Array<MeasurementTypeAndValue>();
    }
    return this.currentRoom.getDisplayData().sort((a, b) => a.type.localeCompare(b.type));
  }

  hasWebcam(): boolean {
    return this.currentRoom.room === '1Aula' || this.currentRoom.hasWebcam();
  }

  getWebcamIp(): string {
    return this.hasWebcam() ? 'https://' + this.currentRoom.getWebcamIp().split(':')[0] : '';
  }

  clickEvent(key: string) {
    ModelmenuComponent.currentFilter = ModelmenuComponent.currentFilter === key ? 'basic' : key;

    const filter = ModelmenuComponent.currentFilter;
    this.applyFilter(filter);
  }

  buttonBackgroundColor(filter: string) {
    return filter === ModelmenuComponent.currentFilter ? 'white' : '';
  }

  buttonColor(filter: string) {
    return filter === ModelmenuComponent.currentFilter ? 'grey' : 'white';
  }

  getCurrentFilter() {
    return ModelmenuComponent.currentFilter;
  }

  filterNotificationActive() {
    return this.getCurrentFilter() !== 'basic';
  }

  testingStuff() {
    ModelController.stuff();
  }
}

