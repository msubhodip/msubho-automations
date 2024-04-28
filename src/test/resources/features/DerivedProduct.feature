@dp
Feature: Derived Products feature

  @dp2 @test3
  Scenario: Validate the duplicate links present in footer of Derived Product Two
    Given I am in DPTwo home page
    When I scrolled down to Footer section
    Then I took all the footer links in a file, also reported the duplicate hyperlinks

