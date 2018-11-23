package com.test.trivago.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.test.trivago.pageObjects.BaseDriver.*;


public class HomePage {
    private WebDriver driver;
    //Locators
    @FindBy(how = How.XPATH, using = "//*[@class='search-icon']")
    private WebElement search;
    @FindBy(how = How.XPATH, using = "//*[@class='input search-input']")
    private WebElement searchData;
    @FindBy(how = How.XPATH, using = "//*[@class='search-results']")
    private WebElement searchResults;

    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void searchEnabled() throws Exception {
        //Click search
        explicitWait(driver, search);
        scrollintoviewElement(driver, search);
        Assert.assertTrue(search.isDisplayed()
                && search.isEnabled(), "Search Icon is not visible and not enabled");
    }

    public void clickSearchIcon() throws Exception {
        //Click search
        scrollintoviewAndClickElement(driver,search);
    }

    public void enterDataSearch(String data) throws Exception {
        scrollintoviewElement(driver, searchData);
        //Clear the field
        searchData.clear();
        //Click search
        searchData.sendKeys(data);
    }

    public void clickSearch() {
        //Click Enter
        searchData.sendKeys(Keys.RETURN);
    }

    public void validateResults() {
        //Click search
        String srchTitle = searchResults.findElement(By.xpath("//*[@class='section-title']")).getText().trim();
        String srchText = searchResults.findElement(By.tagName("span")).getText().trim();
        System.out.println("srchTitle:" + srchTitle + " srchText:" + srchText);

    }
}