	@trivagoPage
Feature: Navigate to Trivago page from Homepage Feature

  @trivago
  Scenario Outline: Click Trivago link from Homepage,search for results and validate results
  Given Trivago is visible
  When I click Trivago
  Then I see the appropriate fields in Trivago searchPage
  When I enter <dataSearch> data in searchPage
  And I click Search button in searchPage
  Then I see the searchResults in Trivago
  And I verify the searchResults in trivago

  Examples:
 | dataSearch |
 | Monterey |



