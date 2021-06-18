Feature: get a sensor

  Background:
    * url baseUrl
    * def insertSensor = read('classpath:at/htl/boundary/SensorResourceTest/sensor-create.feature')
    * call insertSensor

  Scenario: Get a valid sensor from id
    * path 'sensor?id=1405'
    Given method GET
    Then status 202

  Scenario: Get not found when sensor with invalid id is given
    * path 'sensor?id=invalidId'
    Given method GET
    Then status 404

  Scenario: Get all sensor when no id is given
    * path 'sensor'
    Given method GET
    Then status 202
