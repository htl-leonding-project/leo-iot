Feature: delete a sensor

  Background:
    * url baseUrl
    * def insertSensor = read('classpath:at/htl/boundary/SensorResourceTest/sensor-create.feature')
    * call insertSensor

  Scenario: Delete existing Sensor from id
    * path 'sensor?id=1405'
    Given method DELETE
    Then status 202

  Scenario: Delete sensor with invalid id should return not found
    * path 'sensor?id=invalidId'
    Given method DELETE
    Then status 404