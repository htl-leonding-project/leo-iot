Feature: create a sensor

  Background:
    * url baseUrl
    * path 'sensor'

  Scenario: Create a valid sensor without parameters
    Given request
    """
    {

    }
    """
    When method POST
    Then status 202

  Scenario: Create an valid sensor with Thing and SensorType as Parameter
    Given request
    """
    {
      "thing": { },
      "sensorType": { }
    }
    """
    When method POST
    Then status 202

  Scenario: Create an valid sensor with id, Thing and SensorType as Parameter
    Given request
    """
    {
      "id": 1405,
      "thing": { },
      "sensorType": { }
    }
    """
    When method POST
    Then status 202
