import {Component, OnInit, OnDestroy, ViewChild} from '@angular/core';
import {ModelController} from './ModelController/modelController';
import {MqttInterface} from './mqttInterface';
import {MqttService} from 'ngx-mqtt';
import {HistoricalMeasurementService} from '../../core/services/historical-measurements.service';
import {LiveMeasurementService} from '../../core/services/live-measurements.service';
import {ModelmenuComponent} from './modelmenu/modelmenu.component';
import {valueReferenceToExpression} from '@angular/compiler-cli/src/ngtsc/annotations/src/util';
import {element} from 'protractor';

@Component({
  selector: 'app-school3d',
  templateUrl: './school3d.component.html',
  styleUrls: ['./school3d.component.scss']
})
export class School3dComponent implements OnInit {

  @ViewChild('3dContainer', {static: false}) dContainer: any;
  modelController: ModelController;


  constructor(mqttService: MqttService, measurementService: HistoricalMeasurementService, liveService: LiveMeasurementService) {
    const mqttInterface = new MqttInterface(mqttService, measurementService, liveService);

    this.modelController = new ModelController(mqttInterface);
    ModelController.instance = this.modelController;
  }

  ngOnInit(): void {

    document.body.appendChild(ModelController.renderer.domElement);
  }

  modelIsLoading() {
    return ModelController.instance.isLoading;
  }

  legendActive() {
    return ModelmenuComponent.currentFilter !== 'basic';
  }

  getCurrentLegend() {
    return `assets/images/legend_${ModelmenuComponent.currentFilter}.png`;
  }

}
