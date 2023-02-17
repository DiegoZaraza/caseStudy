@Execution
Feature: Automation for alkilautos page

  Scenario: Navigate for alkilautos page to reserve a car
    Given Navigate to alkilautos.com
    When Put the pick up location "El Dorado (BOG)"
    And Select pick up date
    And Select drop off date
    And Click on button search
    Then Validate founded results
    And Select car "COMPACT AUTOMATIC"

