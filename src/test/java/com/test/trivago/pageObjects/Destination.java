package com.test.trivago.pageObjects;

import com.test.trivago.listener.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static com.test.trivago.pageObjects.BaseDriver.*;
import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewAndClickElement;


public class Destination {
    private WebDriver driver;
    //Locators

    //Apply as Developer Button
    @FindBy(how = How.XPATH, using = "//*[@class='nav-icon']")
    public WebElement topLeftIcon;
    @FindBy(how = How.XPATH, using = "//*[contains(@data-src,'taxonomy-united-states-west')]")
    public WebElement destinationMenuItem;
    @FindBy(how = How.XPATH, using = "//*[@class='menu-container']//*[(@class='theme-menu arts-and-culture' and text()='Arts & Culture')]")
    public WebElement bestUsCities;
    @FindBy(how = How.XPATH, using = "//*[@class='article-content']//*[@class='container']/h1")
    public WebElement titleDetailsPage;
    @FindBy(how = How.XPATH, using = "//*[text()='See it on trivago']")
    public WebElement seeItTrivago;
    @FindBy(how = How.XPATH, using = "//*[@id='header-logo-id']")
    public WebElement headerTrivago;
    @FindBy(how = How.XPATH, using = "//*[@name='search-submit']")
    public WebElement searchTrivagoButton;
    @FindBy(how = How.XPATH, using = "//*[@name='sQuery']")
    public WebElement searchFieldTrivago;
    @FindBy(how = How.XPATH, using = "//*[@class='icon-ic btn-horus__icon btn-horus__icon--checkout icon-center']")
    public WebElement searchTrivagoCheckout;
    @FindBy(how = How.XPATH, using = "//*[@class='icon-ic btn-horus__icon btn-horus__icon--checkin icon-center']")
    public WebElement searchTrivagoCheckin;


    //Constructor
    public Destination(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickIcon() throws Exception{
            //Click on Icon
            scrollintoviewAndClickElement(driver, topLeftIcon);
    }

    public void topLeftIconVisible() throws Exception{
            scrollintoviewElement(driver, topLeftIcon);
            Assert.assertTrue((topLeftIcon.isDisplayed() && topLeftIcon.isEnabled()),
                    "topLeftIcon menu is not visible and not Enabled");

    }

    public void verifyMenu() throws Exception{
           if(scroll_Page(destinationMenuItem,1000))
                Assert.assertTrue((destinationMenuItem.isDisplayed() && destinationMenuItem.isEnabled()),
                        "Destination menu Item is not visible and not Enabled");
    }

    public void clickDestinationMenu() throws Exception{
        //Click on Destination Menu
        scrollintoviewAndClickElement(driver,destinationMenuItem);
    }

    public void clickMenuItem() throws Exception{
        //Click on Menu item
        scrollintoviewAndClickElement(driver,destinationMenuItem);
    }

    public void clickSubMenuItem() throws Exception{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if(scroll_Page(bestUsCities,1000))
            executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", bestUsCities);

    }

    public void viewSubMenuItem() throws Exception{
        if(scroll_Page(bestUsCities,1000))
            Assert.assertTrue((bestUsCities.isDisplayed() && bestUsCities.isEnabled()),
                    "Destination sub menu Item is not visible and not Enabled");

    }

    public void seeResults() throws Exception {
        List<WebElement> titles = driver.findElements(By.xpath("//*[@class='post-main-title title']"));
        for(int i=0;i<titles.size();i++){
            WebElement element = driver.findElement(By.xpath("(//*[@class='post-main-title title']/span)["+(i+1)+"]"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            Assert.assertTrue((element.isDisplayed()),
                    "Destination menu results are not visible");
            Assert.assertNotNull(element.getText(), "Text of the Image in search results is not displayed");
        }

    }

    public void validateResults() throws Exception {
        List<WebElement> titles = driver.findElements(By.xpath("//*[@class='post-main-title title']"));
        for(int i=0;i<titles.size();i++){
            WebElement element = driver.findElement(By.xpath("(//*[@class='post-main-title title']/span)["+(i+1)+"]"));
            scrollintoviewElement(driver,element);
            Thread.sleep(500);
            Assert.assertTrue((element.isDisplayed()),
                    "Destination menu results are not visible");
            Assert.assertNotNull(element.getText(), "Text of the Image in search results is not displayed");
        }

    }

    //* Click Result and navigate to trivago page*/
    public void clickSearchResult(String searchResult) throws Exception{
        WebElement element=driver.findElement(By.xpath("//span[text()='"+searchResult+"']"));
        scrollintoviewAndClickElement(driver,element);
        Thread.sleep(500);
        Assert.assertTrue((titleDetailsPage.isDisplayed()),
                "Details page is not visible");
        Assert.assertNotNull((titleDetailsPage.getText()),
                "Title text is not available in the details page");
    }

    public void clickSeeitOnTrivago() throws Exception{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if(scroll_Page(bestUsCities,1000)) {
            scrollintoviewAndClickElement(driver,seeItTrivago);
        }
        switchWindow(driver);
    }

    public void detailsPage() throws Exception{
        Thread.sleep(500);
        Assert.assertTrue((titleDetailsPage.isDisplayed()),
                "Details page is not visible");
        Assert.assertNotNull((titleDetailsPage.getText()),
                "Title text is not available in the details page");
    }

    public void navigateTrivagoSearchPage() throws Exception{
        List<WebElement> checkinCheckou=driver.findElements(By.xpath("//*[@class='btn-horus__content']//*[@class='btn-horus__type']"));
      for(WebElement webElement: checkinCheckou) {
          Assert.assertTrue((webElement.isDisplayed()),
                  "Date & Time selection Calendar is not visible");
          Assert.assertNotNull((webElement.getText()),
                  (webElement.getText())+" is not available in the trivago page");
      }
        Assert.assertTrue((headerTrivago.isDisplayed()),
                "Header Trivago page is not visible");
        Assert.assertNotNull((headerTrivago.getText()),
                "Header Trivago text is not available in the trivago page");
        Assert.assertTrue((searchTrivagoButton.isDisplayed()),
                "Search Trivago button is not visible");
        Assert.assertNotNull((searchTrivagoButton.getText()),
                "Search Trivago text is not available in the trivago page");
        Assert.assertTrue((searchFieldTrivago.isDisplayed()),
                "Search Field Trivago is not visible");
        Assert.assertNotNull((searchFieldTrivago.getText()),
                "Search Field text is not available in the trivago page");
        Assert.assertTrue((searchTrivagoCheckin.isDisplayed()),
                "Search Checkin button is not visible");
        Assert.assertNotNull((searchTrivagoCheckin.getText()),
                "Search Checkin text is not available in the trivago page");
        Assert.assertTrue((searchTrivagoCheckout.isDisplayed()),
                "Search Checkout button is not visible");
        Assert.assertNotNull((searchTrivagoCheckout.getText()),
                "Search Checkout text is not available in the trivago page");
      // Reporter.addStepLog();

    }

    public void verifyRecommendations() throws Exception{

    }
}