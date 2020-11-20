import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { HistoricalMeasurementService } from 'src/app/core/services/historical-measurements.service';
import { MeasurementType, Area, Section } from 'src/app/shared/models';

@Component({
  selector: 'app-grid-layout',
  templateUrl: './grid-layout.component.html',
  styleUrls: ['./grid-layout.component.scss']
})
export class GridLayoutComponent implements OnInit {

  /**
   *The currently displayed MeasurementType
   *
   * @type {MeasurementType}
   * @memberof GridLayoutComponent
   */
  currentMeasurementType: MeasurementType;

  /**
   *The currently displayed Area
   *
   * @type {Area}
   * @memberof GridLayoutComponent
   */
  currentArea: Area = new Area();

  /**
   *The currently displayed Section
   *
   * @type {Section}
   * @memberof GridLayoutComponent
   */
  currentSection: Section;

  /**
   *Changes the row count of historic data components to display a bigger graph and table
   *
   * @memberof GridLayoutComponent
   */
  expandForHistory = false;

  /**
   *Creates an instance of GridLayoutComponent.
   *Setting the default value for the initially displayed floor (this is global and should be changed only here)
   * @param {Router} router
   * @param {ActivatedRoute} route
   * @param {HistoricalMeasurementService} layoutService
   * @memberof GridLayoutComponent
   */
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private layoutService: HistoricalMeasurementService
  ) {
    this.currentArea.name = 'firstfloor';
  }

  ngOnInit() {
    this.validateRoute();
  }

  /**
 *Validating the current route by comparing route params to service data
 *checks if:
 *- area (section doesn't matter) is invalid (redirect to root)
 *- section is invalid, area is valid (redirects to valid area `/dashboard/:area`)
 * @memberof GridComponent
 */
  async validateRoute() {
    this.route.params.subscribe(params => {
      if (params['area']) {
        this.layoutService.getAreas().subscribe(areas => {
          let areaIdx = 0;
          const filteredAreas = areas.filter((a, i) => {
            if (a.name === params['area']) {
              areaIdx = i;
            }
            return a.name === params['area'];
          });
          if (filteredAreas.length > 0) {
            this.currentArea = areas[areaIdx];
            if (params['section']) {
              this.layoutService.getSections(this.currentArea.name).subscribe(sections => {
                let sectIdx = 0;
                const filteredSections = sections.filter((s, i) => {
                  if (s.name === params['section']) {
                    sectIdx = i;
                  }
                  return s.name === params['section'];
                });
                if (filteredSections.length > 0) {
                  this.currentSection = sections[sectIdx];
                } else {
                  this.router.navigate(['/dashboard', this.currentArea.name]);
                }
              });
            }
          } else {
            this.router.navigate(['']);
          }
        });
      }
    });
  }

  changeCurrentArea(area: Area) {
    this.currentArea = area;
    this.changeCurrentSection(null);
    this.changeCurrentMeasurementType(null);
    this.router.navigate(['dashboard', area.name]);
  }

  changeCurrentSection(section: Section) {
    this.currentSection = section;
    this.changeCurrentMeasurementType(null);
    if (section) {
      this.router.navigate(['dashboard', this.currentArea.name, section.name]);
    }
  }

  changeCurrentMeasurementType(mType: MeasurementType) {
    this.currentMeasurementType = mType;
  }

  switchExpantionState(state: boolean) {
    this.expandForHistory = state;
    this.validateRoute();
  }
}
