	@subscribeTag

Feature: Subscribe Feature
    @subscribe
    Scenario Outline: Subscribe for NewsLetter and validate the success message
    Given Subscribe is visible
    When I see the appropriate fields in subscribe
    Then I enter email <email> data
    And I click Confirm
    When I click Submit button in Subscribe
    Then I verify success message <message> in Subscribe

    Examples:
   | email | message |
   | shasha.intell@gmail.com | You are now checked-in! |



