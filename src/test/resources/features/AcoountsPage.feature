Feature: Accounts Page Feature

  Background: user has already logged into application
    Given user is on login page
    When authorised user logs-in correctly
    Then authorised user can access his account

  Scenario: Accounts page validation
    Given user is on Accounts Page
    Then page title should be "My account - My Store"
    Then user gets accounts section
      |ORDER HISTORY AND DETAILS|
      |MY CREDIT SLIPS|
      |MY ADDRESSES|
      |MY PERSONAL INFORMATION|
      |MY WISHLISTS|
    And accounts section count should be 5