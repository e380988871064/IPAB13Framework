
  Feature: Update message
       Background:
        Given     user has valid slack url

       Scenario: Update my message
         When user sends and updates message
         Then status code is 200 should be
         And message is successfully update

