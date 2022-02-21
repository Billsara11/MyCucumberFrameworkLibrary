Feature: Order Page Feature

  Background: Login with correct credentials
    Given user is on login page
    When authorised user logs-in correctly
    Then authorised user can access his account

  Scenario: user validate on the order history page
    Given user on the order history page
    Then user validate land on the user history page

  Scenario: Order history validation
      Given user on the order history page
      Then user validate without any purchase, order page message should be "You have not placed any orders."