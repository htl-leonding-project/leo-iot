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

  Scenario: Create an invalid sensor
    Given request
    """
    {
      "invalidParam": "halloo"
    }
    """
    When method POST
    Then status 404
