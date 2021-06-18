Feature: create a measurement

  Background:
    * url baseUrl
    * path 'measurement'

  Scenario: Create a not valid measurement without parameters
    Given request
    """
    {

    }
    """
    When method POST
    Then status 405

  