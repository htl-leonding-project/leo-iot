Feature: create a measurement

  Background:
    * url baseUrl
    * path 'measurement/add-measurement'

  Scenario: Create a valid measurement with parameters
    Given request
    """
    {
      "jsonObject":
      {
        "measurementKey":
        {
           "timestamp": 1624279520860,
           "sensor": { }
        } ,
        "value": 123456
      }
    }
    """
    When method POST
    Then status 202

  Scenario: Create a not valid measurement without parameters should return 204 (no Content)
    Given request
    """
    {

    }
    """
    When method POST
    Then status 204

