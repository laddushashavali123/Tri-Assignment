	@destinationTag
Feature: Destination Feature

  @destination
  Scenario: Search Destination from topleft Menu and validate the search results
  Given topleftIcon is visible
  When I click topleftIcon
  Then I see Destination menu
  When I click DestinationMenu item
  Then I see DestinationSubMenu item
  When I click DestinationSubMenu item
  Then I see destination results
  And I verify destination results

