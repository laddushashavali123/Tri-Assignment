	@homePageTag
Feature: Home Page Search Feature

  @search
  Scenario Outline: Search Destination in Search Icon and validate the results
     Given Search button is visible
     When I see search icon enabled
     Then I enter <data> in the search field
     When I click on search
     Then I see the search results

   Examples:
    | data |
    | Europe |

