@get
  Feature: List Messages
    Background:
      Given user has valid slack url

      Scenario: Find my message
        When user lists messages
        Then status code should be 200
        And user's message is on the list of messages