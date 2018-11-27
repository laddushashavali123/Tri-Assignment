	@contactTag
Feature: Contact Feature

  @contact
  Scenario Outline: Customer to send the Query in Contact form and validate whether he is able to send the Query
  Given Contact is visible
  When I click Contact
  Then I see the appropriate fields
  When I enter <textArea>,<fullname>,<email> data
  And I check the Confirm checkbox
  And I click Submit button
  Then I see the success message <message>

  Examples:
 | textArea | fullname | email | message |
 | This is a test message | LADDU SHASHAVALI | shasha.intell@gmail.com | Message Sent Successfully! |



