@Login
Feature: Login Page Feature

  Scenario: User on the Login Page
    Given user is on login page
    Then login page title should be "Login - My Store"
    Then forgot your password link should be present


  Scenario: Login with correct credentials
    Given user is on login page
    When authorised user logs-in correctly
    Then authorised user can access his account
  @Skip @Skip_scenario
  Scenario: Login with uncorrected credentials
    Given user is on login page
    When unauthorised user logs-in incorrectly
    Then unauthorised user can not access his account



