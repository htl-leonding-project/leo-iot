Feature: delete an unit

  Background:
    * url baseUrl
    * def insertUnit = read('classpath:at/htl/boundary/UnitResourceTest/unit-create.feature')
    * call insertUnit

  Scenario: Delete existing unit from id
    * path 'unit?id=1405'
    Given method DELETE
    Then status 202

  Scenario: Delete unit with invalid id should return not found
    * path 'unit?id=invalidId'
    Given method DELETE
    Then status 404
