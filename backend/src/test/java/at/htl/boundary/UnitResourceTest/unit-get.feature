Feature: get an unit

  Background:
    * url baseUrl
    * def insertUnit = read('classpath:at/htl/boundary/UnitResourceTest/unit-create.feature')
    * call insertUnit

  Scenario: Get a valid unit from id
    * path 'unit?id=1405'
    Given method GET
    Then status 202

  Scenario: Get not found when unit with invalid id is given
    * path 'unit?id=invalidId'
    Given method GET
    Then status 404

  Scenario: Get all units when no id is given
    * path 'unit'
    Given method GET
    Then status 202
