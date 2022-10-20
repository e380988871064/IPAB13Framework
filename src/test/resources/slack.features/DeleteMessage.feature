
  Feature: Delete message
    you can add free form text -> deleting massage from slack
    Background:
      Given user has valid slack url

      Scenario: Delete my message
        When user sends and delete message
        Then status code is 200 is here
        And message is successfully deleted
