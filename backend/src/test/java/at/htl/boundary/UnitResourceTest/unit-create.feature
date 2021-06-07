Feature: create an unit

  Background:
    * url baseUrl
    * path 'unit'

  Scenario: Create a valid unit without parameters
    Given request
    """
    {
    }
    """
    When method POST
    Then status 202

  Scenario: Create a valid unit with only symbol as parameter
    Given request
    """
    {
      "symbol": "testString"
    }
    """
    When method POST
    Then status 202

  Scenario: Create a valid unit with id and symbol as parameter
    Given request
    """
    {
      "id": 1405,
      "symbol": "testString"
    }
    """
    When method POST
    Then status 202

  Scenario: Create a invalid unit with invalid parameter
    Given request
    """
    {
      "sensorTypeList": ["sensor1", "sensor2", "sensor3"],
      "symbol": "testString"
    }
    """
    When method POST
    Then status 400
