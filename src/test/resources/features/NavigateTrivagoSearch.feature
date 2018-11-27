	@destinationTag
Feature:Search Destination from topleft Menu and validate the search results

  @navigateSearch
  Scenario Outline: Search Destination Feature,click on the option and navigate to trivago search portal
  Given topleftIcon is visible
  When I click topleftIcon
  Then I see Destination menu
  When I click DestinationMenu item
  Then I see DestinationSubMenu item
  When I click DestinationSubMenu item
  Then I see destination results
  And I verify destination results
  When I click on searchresult <searchOption>
  Then I navigate to details page
  When I click see it on trivago
  Then I navigate to trivagoSearch page
  And I see recommendations

   Examples:
   | searchOption |
   | A Stay by the Bay: Affordable San Francisco Hotels near the Downtown |

