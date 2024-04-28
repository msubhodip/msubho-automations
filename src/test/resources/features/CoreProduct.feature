@TestSuit
Feature: Core Product Feature

  @test1 @cp
  Scenario: User counts total number of Videos Feeds and count the videos feeds those are present in the page greater than and equals Three d
    Given I am in CP home page
    When I navigate to "News & Features" from more options
    Then I counted "videos" feed
    And I counted "videos" feed with greater than equals "3d"
