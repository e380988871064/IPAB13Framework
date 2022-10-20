@get
Feature: Slack post message

  Scenario: Successfully sending message to slack channel
    Given user has valid slack url
    When user send a message to Slack channel
    Then status code is 200
    And message is successfully delivered
