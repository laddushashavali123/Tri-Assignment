package com.test.trivago.pageObjects;

import com.test.trivago.listener.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.test.trivago.pageObjects.BaseDriver.*;


public class HomepageTrivago {
    private WebDriver driver;
    //Locators
    @FindBy(how = How.XPATH, using = "//*[text()='trivago']")
    public WebElement trivagoLink;
    @FindBy(how = How.XPATH, using = "//*[@class='input horus__querytext']")
    public WebElement dataField;
    @FindBy(how = How.NAME, using = "search-submit")
    public WebElement submit;
    @FindBy(how = How.XPATH, using = "//*[@class='horus-btn-search__icon icon-ic icon-contain icon-bg-icn_search_light']")
    public WebElement searchTrivagoButton;
    @FindBy(how = How.XPATH, using = "//*[@name='sQuery']")
    public WebElement searchFieldTrivago;
    @FindBy(how = How.XPATH, using = "//*[@class='icon-ic btn-horus__icon btn-horus__icon--checkout icon-center']")
    public WebElement searchTrivagoCheckout;
    @FindBy(how = How.XPATH, using = "//*[@class='icon-ic btn-horus__icon btn-horus__icon--checkin icon-center']")
    public WebElement searchTrivagoCheckin;

    //Constructor
    public HomepageTrivago(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void trivagoVisible() throws Exception {
        //Contact is visible
        scrollintoviewElement(driver, trivagoLink);
        Reporter.addStepLog("Contact link is visible and clickable in the application");
    }

    public void clickTrivago() throws Exception {
        //Click contact
        scrollintoviewAndClickElement(driver, trivagoLink);
        //Switch to the new window
        switchWindow(driver);
    }

    public void verifyFields() throws Exception {
        scrollintoviewElement(driver, dataField);
        scrollintoviewElement(driver, submit);

        Assert.assertTrue((dataField.isDisplayed() && dataField.isEnabled())
                        && (submit.isDisplayed() && submit.isEnabled()),
                "Data field and Submit button are not visible");
    }

    public void enterData(String data) throws Exception {
        scrollintoviewElement(driver, dataField);
        //Clear the field
        dataField.clear();
        //Enter message
        dataField.sendKeys(data);
    }

    public void clickSearch() throws Exception {
        //Click confirm
        scrollintoviewAndClickElement(driver, submit);
    }

    public void navigateTrivagoSearchPage() throws Exception {
        // Switched to page
        String titleTrivago = driver.getTitle();
        Reporter.addStepLog("Moved to page with title: " + titleTrivago);
        switchWindow(driver, titleTrivago);
        Assert.assertNotNull((titleTrivago),
                "Header Trivago text is not Visible in the trivago page");
        Thread.sleep(1000);

        ((JavascriptExecutor) driver)
                .executeScript("window.onafterunload = function(e){};");
        Thread.sleep(2000);
        WebElement clickClose=null;
        List<WebElement> checkinCheckou = driver.findElements(By.xpath("//*[@class='btn-horus__content']//*[@class='btn-horus__type']"));
        for (int i = 0; i < checkinCheckou.size(); i++) {
            WebElement webElement = driver.findElement(By.xpath("(//*[@class='btn-horus__content']//*[@class='btn-horus__type'])[" + (i + 1) + "]"));
            Assert.assertTrue((webElement.isDisplayed()),
                    "Date & Time selection Calendar is not visible");
            Assert.assertNotNull((webElement.getText()),
                    (webElement.getText()) + " is not available in the trivago page");
           /* if (isElementPresent(webElement)) {
                Thread.sleep(2000);
                webElement.click();
                clickClose=webElement;
            }*/
            Reporter.addStepLog(webElement.getText() + " is available in the trivago page");
        }

        //clickClose.click();
        Thread.sleep(1000);
        WebElement srchText = driver.findElement(By.xpath("//*[@class='input horus__querytext']"));
        scrollintoviewElement(driver, srchText);
        Assert.assertTrue((searchTrivagoButton.isDisplayed()),
                "Search Trivago button is not visible");
        Assert.assertNotNull((searchTrivagoButton.getText()),
                "Search Trivago text is not visible in the trivago page");
        Reporter.addStepLog("Search Trivago is available in the trivago page");

        Assert.assertTrue((searchFieldTrivago.isDisplayed()),
                "Search Field Trivago is not visible");
        Assert.assertNotNull((searchFieldTrivago.getText()),
                "Search Field text is not visible in the trivago page");
        Reporter.addStepLog("Search Field text is available in the trivago page");

        Assert.assertTrue((searchTrivagoCheckin.isDisplayed()),
                "Search Checkin button is not visible");
        Assert.assertNotNull((searchTrivagoCheckin.getText()),
                "Search Checkin text is not available in the trivago page");
        Reporter.addStepLog("Search Checkin Text is available in the trivago page");

        Assert.assertTrue((searchTrivagoCheckout.isDisplayed()),
                "Search Checkout button is not visible");
        Assert.assertNotNull((searchTrivagoCheckout.getText()),
                "Search Checkout text is not available in the trivago page");
        Reporter.addStepLog("Search Checkout text is available in the trivago page");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>TrivagoResults</a>");
    }

    public void verifyRecommendations() throws Exception {
        //Invivbility of Guest ratings element
        implicitWait(driver);
        Thread.sleep(3000);

        List<WebElement> searchResultSet = driver.findElements(By.xpath("//*[@class='pos-relative item__wrapper']"));
        Reporter.addStepLog("'"+searchResultSet.size()+"' Search Results are displayed in the trivago page");
        for (int i = 0; i < searchResultSet.size(); i++) {
            String Name="//*[@class='hotel-item item-order__list-item js_co_item']";
            WebElement resultWrapper = driver.findElement(By.xpath("(//*[@class='pos-relative item__wrapper'])[" + (i + 1) + "]"));
            scrollintoviewElement(driver, resultWrapper);
            //Verify Image
            String lstResultID=driver.findElement(By.xpath(Name)).getAttribute("id");
            Reporter.addStepLog("****  List Item: " + lstResultID+" ****");
            Reporter.addStepLog("Image Gallery: " + lstResultID);
            //Verify Result details
            WebElement resultItemdetails = resultWrapper.findElement(By.xpath("//*[@class='item__flex-column']//*[@class='item__details']"));
            WebElement itemName = resultItemdetails.findElement(By.xpath("(//*[@class='item__name']/h3//*[@class='item-link name__copytext'])"));
            Assert.assertNotNull(itemName.getText());
            Reporter.addStepLog("Name of list Item: " + itemName.getText());
            List<WebElement> starsReview = resultItemdetails.findElements(By.xpath("(//*[@class='item__name']//*[@class='item__stars-badges']//*[@class='item__stars-wrp']//*[@class='icon-ic item__star'])"));
            Reporter.addStepLog("List Item: " + itemName.getText() + " has " + starsReview.size() + " stars");
            WebElement accomodation = resultItemdetails.findElement(By.xpath("(//*[@class='item__name']//*[@class='item__stars-badges']//*[@class='item__accommodation-type'])"));
            Assert.assertNotNull(accomodation.getText());
            Reporter.addStepLog("Accomodation Type of list Item: " + accomodation.getText());
            WebElement location = resultItemdetails.findElement(By.xpath("//*[@class='item__location ov-hidden']"));
            Assert.assertNotNull(location.getText());
            Reporter.addStepLog("Location of " + accomodation.getText() + " is: " + location.getText());
            //Verify Result Section Deal
            WebElement resultItemSectionDeal = resultWrapper.findElement(By.xpath("//*[@class='item__flex-column']//*[@class='item__deal-other pos-relative tem__deal-other--no-deals']"));
            //Other Deals Heading
            WebElement otherDealsHeading = resultItemSectionDeal.findElement(By.xpath("//*[@class='visuallyhidden']"));
            Assert.assertNotNull(otherDealsHeading.getText());
            Reporter.addStepLog("Other Deals Heading: " + otherDealsHeading.getText());
            //List of deals
            WebElement dealAlternative = resultItemSectionDeal.findElement(By.xpath("//*[@class='deal-other__top-alternatives']"));
            List<WebElement> dealsList = dealAlternative.findElements(By.xpath("//*[@class='deal-other__offer js_co_deal']"));
            List<String> lstDeals=new ArrayList<String>();
            for (int j = 0; j < dealsList.size(); j++) {
                WebElement dealName = dealAlternative.findElement(By.xpath("(//*[@class='deal-other__offer js_co_deal'])[" + (j + 1) + "]"));
                Assert.assertNotNull(dealName.getText());lstDeals.add(dealName.getText());
            }
            Reporter.addStepLog("Deals : " + lstDeals);

            //More Deals
            WebElement slideMoreDeals = resultItemSectionDeal.findElement(By.xpath("//*[@class='deal-other__more item__slideout-toggle']"));
            Assert.assertNotNull(slideMoreDeals.getText());
            Reporter.addStepLog("Slide More Deals: " + slideMoreDeals.getText());
            //Verify Result Section Best Deal
            WebElement resultItemSectionBestDeal = resultWrapper.findElement(By.xpath("//*[@class='item__flex-column']//*[@class='item__deal-best']//*[@class='item__deal-best-link item-link no_availability reduced js_co_link']"));

            WebElement itemWrapperDeal = resultItemSectionBestDeal.findElement(By.xpath("//*[@class='has-tooltip item__flag-wrapper']"));
            Assert.assertNotNull(itemWrapperDeal.getText());
            Reporter.addStepLog("Item Wrapper Deal: " + itemWrapperDeal.getText());
            //Strike Section
            WebElement strikeThrough = resultItemSectionBestDeal.findElement(By.xpath("//*[@class='item__best-details item__best-details--with-strikethrough']"));
            Assert.assertNotNull(strikeThrough.getText());
            Reporter.addStepLog("Strike through: " + strikeThrough.getText());
            //Strike wrapper Section
            WebElement dealWrapper = resultItemSectionBestDeal.findElement(By.xpath("//*[@class='deal__wrapper']"));
            Assert.assertNotNull(dealWrapper.getText());
            Reporter.addStepLog("Strike wrapper: " + dealWrapper.getText());

            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>"+lstResultID+"</a>");
        }
    }
}