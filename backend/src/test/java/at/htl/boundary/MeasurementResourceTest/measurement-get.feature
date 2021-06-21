Feature: get a measurement

  Background:
    * url baseUrl

  Scenario: Get an empty measurement
    * path 'measurement?from=12&to=14'
    Given method GET
    Then status 202

  Scenario: Get a measurement
    * path 'measurement?from=12&to=14&sensor=1'
    Given method GET
    Then status 202

  Scenario: Try getting a measurement with invalid parameters
    * path 'measurement?from=12&to=hallo&sensor=1'
    Given method GET
    Then status 404


