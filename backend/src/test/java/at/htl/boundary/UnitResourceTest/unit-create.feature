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
