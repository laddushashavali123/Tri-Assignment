package com.test.trivago.stepdefinitions;

import com.test.trivago.listener.Reporter;
import com.test.trivago.pageObjects.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.test.trivago.pageObjects.BaseDriver.captureScreen;

public class SearchContactDestinationSubsribeSteps {
    private WebDriver driver;
    private HomePage homePage;
    private Subscribe subscribe;
    private Contact contact;
    private Destination destination;

    @Before
    public void beforeScenario(Scenario scenario) {
        //Open URL
        BaseDriver baseDriver = new BaseDriver();
        driver = baseDriver.getDriver();
        //PageObjects
        homePage = new HomePage(driver);
        contact = new Contact(driver);
        subscribe = new Subscribe(driver);
        destination = new Destination(driver);

        if (!scenario.getName().equals("")) {
            Reporter.assignAuthor("Laddu shashavali");
            Reporter.addScenarioLog("Scenario: " + scenario.getName());
        }
    }

    @Given("^Contact is visible$")
    public void contact_is_visible() throws Exception {
        Reporter.addStepLog("Contact Link in the application");
        contact.contactVisible();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ContactVisible</a>");
    }

    @When("^I click Contact$")
    public void i_click_contact_page() throws Exception {
        Reporter.addStepLog("Click Contact Link in the application");
        contact.clickContact();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickContact</a>");
    }

    @When("^I enter (.+),(.+),(.+) data$")
    public void i_enter_and_data(String textarea, String fullname, String email) throws Exception {
        Reporter.addStepLog("Enter Fields in Contact Form in the application");
        contact.enterDataContactDetails(textarea, fullname, email);
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>EnterFields</a>");

    }

    @Then("^I see the appropriate fields$")
    public void i_see_the_appropriate_fields_contact() throws Exception {
        Reporter.addStepLog("Verify Fields in Contact Form in the application");
        contact.verifyFields();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>VerifyFields</a>");
    }

    @Then("^I see the success message (.+)$")
    public void i_see_the_success_message(String message) throws Exception {
        Reporter.addStepLog("Success message in the application");
        if (contact.verifyMessage(message)) {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        } else {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        }
    }

    @And("^I check the Confirm checkbox$")
    public void i_check_the_confirm_checkbox() throws Exception {
        Reporter.addStepLog("Click cpnfirm button in the application");
        contact.clickConfirm();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickConfirm</a>");
    }

    @And("^I click Submit button$")
    public void i_click_submit_button() throws Exception {
        Reporter.addStepLog("Click submit button in the application");
        contact.clickSubmit();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubmit</a>");
    }
    @Given("^topleftIcon is visible$")
    public void toplefticon_is_visible() throws Exception {
        Reporter.addStepLog("Top left Icon in the application");
        destination.topLeftIconVisible();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>TopleftIcon</a>");
    }

    @When("^I click topleftIcon$")
    public void i_click_toplefticon() throws Exception {
        Reporter.addStepLog("Click Topleft Icon in the application");
        destination.clickIcon();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickIcon</a>");
    }

    @When("^I click DestinationMenu item$")
    public void i_click_destinationmenu_item() throws Exception {
        Reporter.addStepLog("Destination Menu Item is clicked in the application");
        destination.clickDestinationMenu();
        destination.clickMenuItem();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickMenuItem</a>");
    }

    @When("^I see DestinationSubMenu item$")
    public void i_see_destinationsubmenu_item() throws Exception {
        Reporter.addStepLog("Destination SubMenu Item is visible in the application");
        destination.viewSubMenuItem();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubMenuItem</a>");
    }

    @When("^I click DestinationSubMenu item$")
    public void i_click_destinationsubmenu_item() throws Exception {
        Reporter.addStepLog("Destination SubMenu Item is clicked in the application");
        destination.clickSubMenuItem();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubMenuItem</a>");
    }

    @Then("^I see Destination menu$")
    public void i_see_destination_menu() throws Exception {
        Reporter.addStepLog("Destination Menu in the application");
        destination.verifyMenu();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>VerifyMenu</a>");
    }

    @Then("^I see destination results$")
    public void i_see_destination_results_destination() throws Exception {
        Reporter.addStepLog("Destination results in the application");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>DestinationResults</a>");
        destination.seeResults();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "DestinationResults");
    }

    @And("^I verify destination results$")
    public void i_verify_destination_results() throws Exception {
        Reporter.addStepLog("Validate Destination results in the application");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ValidateDestinationResults</a>");
        destination.validateResults();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "ValidateDestinationResults");
    }
    @Given("^Subscribe is visible$")
    public void subscribe_is_visible() throws Exception {
        Reporter.addStepLog("Subscribe is visible in the application");
        subscribe.fieldsVisible();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "SubscribeVisible");

    }

    @When("^I see the appropriate fields in subscribe$")
    public void i_see_the_appropriate_fields_in_subscribe() throws Exception {
        Reporter.addStepLog("Appropriate fields are visible in the application");
        subscribe.fieldsVisible();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>FieldSubscribe</a>");
    }

    @When("^I click Submit button in Subscribe$")
    public void i_click_submit_button_subscribe() throws Exception {
        Reporter.addStepLog("Click submit button in the application");
        subscribe.clickSubmit();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubmit</a>");
    }

    @And("^I click Confirm$")
    public void i_click_confirm_subscribe() throws Exception {
        Reporter.addStepLog("Subscribe is visible in the application");
        subscribe.clickConfirm();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SubscribeVisible</a>");
    }

    @Then("^I verify success message (.+) in Subscribe$")
    public void i_see_the_success_message_subscribe(String message) throws Exception {
        Reporter.addStepLog("See the success message in the application");
        if (subscribe.verifyMessage(message)) {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        } else {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        }
    }

    @Then("^I enter email (.+) data$")
    public void i_enter_data(String email) throws Exception {
        Reporter.addStepLog("Eneter email in the application");
        subscribe.enteremail(email);
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>Enteremail</a>");
    }

    @Given("^Search button is visible$")
    public void search_button_Visible() throws Exception {
        Reporter.addStepLog("Search Icon or button is Visible");
        homePage.searchEnabled();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "SearchIcon");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SearchIcon</a>");
    }

    @When("^I see search icon enabled$")
    public void searchButtonEnabled() throws Exception {
        Reporter.addStepLog("Click on Search icon");
        homePage.clickSearchIcon();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSearchIcon</a>");
    }

    @Then("^I enter (.+) in the search field$")
    public void enterDataSearch(String data) throws Exception {
        Reporter.addStepLog("Enter data to search");
        homePage.enterDataSearch(data);
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>EnterData</a>");
    }

    @When("^I click on search$")
    public void searchClick() throws Exception {
        Reporter.addStepLog("Click on Search button to see the results");
        homePage.clickSearch();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSearchButton</a>");
    }

    @Then("^I see the search results$")
    public void searchResults() throws Exception {
        Reporter.addStepLog("Validating the results in the application");
        homePage.validateResults();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SearchResults</a>");
    }
    //Navigate to trivago portal
    @When("^I click on searchresult (.+)$")
    public void i_click_on_searchresult(String searchoption) throws Exception {

    }

    @When("^I click see it on trivago$")
    public void i_click_see_it_on_trivago() throws Exception {

    }

    @Then("^I navigate to details page$")
    public void i_navigate_to_details_page() throws Exception {

    }

    @Then("^I navigate to trivagoSearch page$")
    public void i_navigate_to_trivagosearch_page() throws Exception {

    }

    @And("^I see recommendations$")
    public void i_see_recommendations() throws Exception {

    }
    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>TestFail</a>");
                byte[] screenshotFail = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshotFail, "image/png");
            } else {
                byte[] screenshotPass = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshotPass, "image/png");
            }
            //Quit all Instances
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
