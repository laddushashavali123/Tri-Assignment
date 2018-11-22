package com.test.trivago.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static com.test.trivago.pageObjects.BaseDriver.scroll_Page;
import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewElement;


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
    @FindBy(how = How.XPATH, using = "//*[@class='post-main-title title']")
    public WebElement titleResult;


    //Constructor
    public Destination(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickIcon() {
        try {
            scrollintoviewElement(driver, topLeftIcon);
            //Click icon
            topLeftIcon.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void topLeftIconVisible() {
        try {
            scrollintoviewElement(driver, topLeftIcon);
            Assert.assertTrue((topLeftIcon.isDisplayed() && topLeftIcon.isEnabled()),
                    "topLeftIcon menu is visible and Enabled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyMenu() {
        try {
            if(scroll_Page(destinationMenuItem,1000))
                Assert.assertTrue((destinationMenuItem.isDisplayed() && destinationMenuItem.isEnabled()),
                        "Destination menu Item is visible and Enabled");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickDestinationMenu() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", destinationMenuItem);

    }

    public void clickMenuItem() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", destinationMenuItem);
    }

    public void clickSubMenuItem() {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if(scroll_Page(bestUsCities,1000))
            executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", bestUsCities);

    }

    public void viewSubMenuItem() {
        if(scroll_Page(bestUsCities,1000))
            Assert.assertTrue((bestUsCities.isDisplayed() && bestUsCities.isEnabled()),
                    "Destination sub menu Item is visible and Enabled");

    }

    /*  public void seeResults() {
          try {
              if(scroll_Page(titleResult,1000))
              Assert.assertTrue((titleResult.isDisplayed()),
                      "Destination menu results are visible");
          } catch (Exception e) {
              e.printStackTrace();
          }

      }

      public void validateResults() {
          try {
              scrollintoviewElement(driver, titleResult);
          } catch (Exception e) {
              e.printStackTrace();
          }
          //Click search
          String srchTitle = titleResult.getText().trim();
          Assert.assertNotNull(srchTitle);

      }*/
    public void seeResults() throws Exception {
        List<WebElement> titles = driver.findElements(By.xpath("//*[@class='post-main-title title']"));
        for(int i=0;i<titles.size();i++){
            WebElement element = driver.findElement(By.xpath("(//*[@class='post-main-title title']/span)["+(i+1)+"]"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            Assert.assertTrue((element.isDisplayed()),
                    "Destination menu results are visible");
            Assert.assertNotNull(element.getText(), "Text of the Image in search results is displayed");
        }

    }

    public void validateResults() throws Exception {

        List<WebElement> titles = driver.findElements(By.xpath("//*[@class='post-main-title title']"));
        for(int i=0;i<titles.size();i++){
            WebElement element = driver.findElement(By.xpath("(//*[@class='post-main-title title']/span)["+(i+1)+"]"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            Assert.assertTrue((element.isDisplayed()),
                    "Destination menu results are visible");
            Assert.assertNotNull(element.getText(), "Text of the Image in search results is displayed");
        }

    }
}