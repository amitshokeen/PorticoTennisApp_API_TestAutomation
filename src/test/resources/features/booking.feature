@smoke @regression
Feature: Bookings API

  @bookAndCancel
  Scenario: Confirm and cancel a booking
    Given I am logged in
    When I book a slot today from "06:00" to "07:00"
    Then the booking should be confirmed
    When I cancel the booking on "today"
    Then the booking should be cancelled