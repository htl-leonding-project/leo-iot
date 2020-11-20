import { TestBed } from '@angular/core/testing';

import { HistoricalMeasurementService } from './historical-measurements.service';
import { HttpClientModule } from '@angular/common/http';
import { LanguageService } from './language.service';
import { HttpClient } from 'selenium-webdriver/http';

describe('HistoricalMeasurementService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule],
    providers: [HistoricalMeasurementService, LanguageService]
  }));

  it('should be created', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);
    expect(service).toBeTruthy();
  });
/*
  // Tests the method getAreas, to return a value (not null)
  it('return value of method getAreas should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getAreas()
      .subscribe(data => { expect(data).not.toBeNull(); });
    // expect(service.getAreas('en')).not.toBeNull();
  });

  // Tests the method getSections, to return a value (not null)
  it('return value of method getSections should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getSections('firstfloor')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });

  // Tests the method getMeasurementTypesOfRoom, to return a value (not null)
  it('return value of method getMeasurementTypesOfRoom should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getMeasurementTypesOfRoom('firstfloor', 'E582', 'PC')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });

  // Tests the method getSensorsOfSection, to return a value (not null)
  it('return value of method getSensorsOfSection should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getSensorsOfSection('firstfloor', 'E582')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });

  // Tests the method getDailyAvgOfSensorFromPeriod, to return a value (not null)
  it('return value of method getDailyAvgOfSensorFromPeriod should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getDailyAvgOfSensorFromPeriod('firstfloor', 'E582', 'co2', '2018-01-01', '2019-12-31')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });

  // Tests the method getAllDailyHourValues, to return a value (not null)
  it('return value of method getAllDailyHourValues should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getAllDailyHourValues('firstfloor', 'E582', 'co2', '2018-10-10')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });

  // Tests the method getAverageValueOfSensortypeBetweenDate, to return a value (not null)
  it('return value of method getAverageValueOfSensortypeBetweenDate should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getAverageValueOfSensortypeBetweenDate('firstfloor', 'E582', 'co2', '2018-01-01', '2018-12-31')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });

  // Tests the method getMonthlyAvgValueFromPeriod, to return a value (not null)
  it('return value of method getMonthlyAvgValueFromPeriod should not be null', () => {
    const service: HistoricalMeasurementService = TestBed.get(HistoricalMeasurementService);

    service.getMonthlyAvgValueFromPeriod('firstfloor', 'E582', 'co2', '2018', '10', '10')
      .subscribe(data => { expect(data).not.toBeNull(); });
  });*/
});
