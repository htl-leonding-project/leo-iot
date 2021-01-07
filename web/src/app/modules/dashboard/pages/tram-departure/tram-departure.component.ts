import { Component, OnInit } from '@angular/core';
import { TramService } from 'src/app/core/services/tram.service';
import { Trip } from 'src/app/shared/models';

@Component({
  selector: 'app-tram-departure',
  templateUrl: './tram-departure.component.html',
  styleUrls: ['./tram-departure.component.scss']
})
export class TramDepartureComponent implements OnInit {

  /**
   *Trip containing the data for departure and arrival time and location
   *
   * @type {Trip}
   * @memberof TramDepartureComponent
   */
  trip: Trip;

  /**
   *Creates an instance of TramDepartureComponent.
   * @param {TramService} tramService
   * @memberof TramDepartureComponent
   */
  constructor(private tramService: TramService) { }

  ngOnInit() {
    this.tramService.getNextDeparture().subscribe(data => {
      this.trip = data;
      console.log(data)
    });
  }

}
