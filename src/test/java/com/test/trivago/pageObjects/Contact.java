package com.test.trivago.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewAndClickElement;
import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewElement;
import static com.test.trivago.pageObjects.BaseDriver.switchWindow;


public class Contact {
    private WebDriver driver;
    //Locators
    @FindBy(how = How.XPATH, using = "//*[text()='Contact']")
    public WebElement contact;
    @FindBy(how = How.XPATH, using = "//textarea[@class='contact-msg']")
    public WebElement contactTextArea;
    @FindBy(how = How.XPATH, using = "//*[@class='contact-input']")
    public WebElement fullName;
    @FindBy(how = How.ID, using = "contact-email")
    public WebElement email;
    @FindBy(how = How.ID, using = "confirm")
    public WebElement confirm;
    @FindBy(how = How.XPATH, using = "//*[text()='Submit']")
    public WebElement submit;
    @FindBy(how = How.XPATH, using = "//*[@class='feedback']")
    public WebElement feedBack;

    //Constructor
    public Contact(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void contactVisible() throws Exception {
        //Click search
        scrollintoviewElement(driver, contact);
    }

    public void clickContact() throws Exception {
        //Click search
        scrollintoviewAndClickElement(driver, contact);
        //Switch to the new window
        switchWindow(driver);
    }

    public void enterDataContactDetails(String message,
                                        String fullname,
                                        String mailID) throws Exception {
        scrollintoviewElement(driver, contactTextArea);
        //Clear the field
        contactTextArea.clear();
        //Click search
        contactTextArea.sendKeys(message);
        //enter Fullname
        scrollintoviewElement(driver, fullName);
        fullName.clear();
        fullName.sendKeys(fullname);
        //enter email
        scrollintoviewElement(driver, email);
        email.clear();
        email.sendKeys(mailID);
    }

    public void verifyFields() throws Exception {
        scrollintoviewElement(driver, contactTextArea);
        scrollintoviewElement(driver, fullName);
        scrollintoviewElement(driver, email);
        scrollintoviewElement(driver, confirm);
        Assert.assertTrue((contactTextArea.isDisplayed() && contactTextArea.isEnabled())
                        && (fullName.isDisplayed() && fullName.isEnabled())
                        && (email.isDisplayed() && email.isEnabled())
                        && (confirm.isDisplayed() && confirm.isEnabled()),
                "Text Area , Fullname ,email ID and Confirm checkBox");
    }

    public void clickConfirm() throws Exception {
        //Click checkbox
        scrollintoviewAndClickElement(driver, confirm);
    }

    public void clickSubmit() throws Exception {
        //Cick on submit button
        scrollintoviewAndClickElement(driver, submit);
    }

    //Verify message 'Message Sent Successfully! '
    public Boolean verifyMessage(String message) throws Exception {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-250)", "");
        scrollintoviewElement(driver, feedBack);
        return feedBack.getText().toUpperCase().trim().equals(message.toUpperCase().trim());
    }
}