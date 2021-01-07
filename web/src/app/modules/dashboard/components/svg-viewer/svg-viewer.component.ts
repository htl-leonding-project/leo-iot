import {
  Component,
  OnInit,
  OnChanges,
  Input,
  Output,
  EventEmitter,
  SimpleChanges,
  AfterViewInit
} from '@angular/core';
import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { Area, Section } from 'src/app/shared/models';

import * as SvgPanZoom from 'svg-pan-zoom';

@Component({
  selector: 'app-svg-viewer',
  templateUrl: './svg-viewer.component.html',
  styleUrls: ['./svg-viewer.component.scss']
})
export class SvgViewerComponent implements OnInit, OnChanges, AfterViewInit {

  options = {
    preventMouseEventsDefault: false
  };

  /**
   *Input to specify the displayed area (displayed svg and selection on floor-switch componet)
   *
   * @type {Area}
   * @memberof SvgViewerComponent
   */
  @Input() displayArea: Area;

  /**
   *Input to specify the displayed section (highlighted room)
   *
   * @type {Section}
   * @memberof SvgViewerComponent
   */
  @Input() displaySection: Section;

  /**
   *Outputs a section when it has been clicked
   *
   * @memberof SvgViewerComponent
   */
  @Output() sectionClicked = new EventEmitter<Section>();

  /**
   *Outputs the area if it changed
   *
   * @memberof SvgViewerComponent
   */
  @Output() areaClicked = new EventEmitter<Area>();

  /**
   *To apply highlighting to the selected section by the name of the room
   *
   * @type {String}
   * @memberof SvgViewerComponent
   */
  selectedSection: string;

  /**
   *Fetched sections of current area
   *
   * @type {Section[]}
   * @memberof SvgViewerComponent
   */
  sections: Section[] = [];

  /**
   *Pan zoom instance to manage zoom and position
   *
   * @type {SvgPanZoom.Instance}
   * @memberof SvgViewerComponent
   */
  svgPanZoom: SvgPanZoom.Instance;

  /**
   *Creates an instance of SvgViewerComponent.
   * @memberof SvgViewerComponent
   */
  constructor(private layoutService: HistoricalMeasurementService) {
  }

  /**
   *First fetching all sections for area if they are null
   *
   * @memberof SvgViewerComponent
   */
  ngOnInit() {
    if (this.displayArea) {
      if (this.displayArea.name !== '') {
        this.layoutService.getSections(this.displayArea.name).subscribe(sections => {
          this.sections = sections;
        });
      }
    }
  }

  /**
   *Initialinzing the pan zoom by applying it to a svg element
   *
   * @memberof SvgViewerComponent
   */
  ngAfterViewInit(): void {
    if (this.displayArea) {
      if (this.displayArea.name !== '') {
        this.svgPanZoom = SvgPanZoom(`#${this.displayArea.name}`, this.options);
      }
    }
  }

  /**
   *Listening for changes to the displayArea Input and fetching Sections on change
   *
   * @param {SimpleChanges} changes
   * @memberof SvgViewerComponent
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes.displayArea) {
      if (changes.displayArea.currentValue !== this.displayArea) {
        this.displayArea = changes.displayArea.currentValue;

      }
    }
    if (changes.displaySection) {
      this.displaySection = changes.displaySection.currentValue;
      if (this.displaySection) {
        this.selectedSection = this.displaySection.name; // change the highlighting
      }
    }
    this.layoutService.getSections(this.displayArea.name).subscribe(sections => {
      this.sections = sections;
    });
  }

  /**
   *CRITICAL!
   *
   *Called on section hitbox click and emits the section with the source elements id
   *which is hardcoded into the markup and equal to section names in the system
   *Maybe theres a better way to dynamically generate section hitboxes
   *but this solution works well enough as long as section names in the backend dont change
   *
   *If the svg viewer doesnt show the areas maybe some of the hardcoded ids need to be changed
   *
   * @param {*} event
   * @memberof SvgViewerComponent
   */
  emitSection(event: any) {
    // if (this.selectedSection === event.srcElement.id) { return; } // if its already selected, do nothing
    let sectIdx = -1;
    this.sections.filter((s, i) => {
      if (s.name === event.srcElement.id) { sectIdx = i; }
      return s.name === event.srcElement.id;
    });
    // a quick fix (needs to be changed)
    if (event.srcElement.id === 'aula') {
      const a = new Section();
      a.name = 'aula';
      this.sectionClicked.emit(a);
      return;
    }
    if (sectIdx > -1) {
      this.selectedSection = event.srcElement.id;
      this.sectionClicked.emit(this.sections[sectIdx]);
    }
  }

  /**
   *Changes the displayed floor svg by changing the ng-switched property and applying svg pan zoom
   *
   * @param {Area} area
   * @memberof SvgViewerComponent
   */
  changeFloor(area: Area) {
    console.log(area);
    this.displayArea = area;
    this.selectedSection = ''; // resetting the room highlighting
    // we need a 0ms timeout here because then this callback gets added to the angular callback stack
    // and we can acces the element after the ng switch
    setTimeout(() => {
      this.svgPanZoom.destroy();
      this.svgPanZoom = SvgPanZoom(`#${this.displayArea.name}`, this.options);
      this.resetZoom();
    }, 0);
    this.areaClicked.emit(area);
  }

  /**
   *Pan zooms in by 10%
   *
   * @memberof SvgViewerComponent
   */
  zoomIn() {
    this.svgPanZoom.zoomBy(2);
  }

  /**
   *Pan zooms out by 10%
   *
   * @memberof SvgViewerComponent
   */
  zoomOut() {
    this.svgPanZoom.zoomBy(0.5);
  }

  /**
   *Resets the pan zoom to 100%
   *
   * @memberof SvgViewerComponent
   */
  resetZoom() {
    if (this.svgPanZoom) {
      this.svgPanZoom.resetZoom();
      this.svgPanZoom.resetPan();
    }
  }
}
