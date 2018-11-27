package com.test.trivago.pageObjects;

import com.test.trivago.listener.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.test.trivago.pageObjects.BaseDriver.*;


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
        //Contact is visible
        Thread.sleep(1000);
        scrollintoviewElement(driver, contact);
        Reporter.addStepLog("Contact link is visible and clickable in the application");
    }

    public void clickContact() throws Exception {
        //Click contact
        Thread.sleep(1000);
        scrollintoviewAndClickElement(driver, contact);
        //Switch to the new window
        switchWindow(driver);
    }

    public void enterDataContactDetails(String message,
                                        String fullname,
                                        String mailID) throws Exception {
        Thread.sleep(1000);
        scrollintoviewElement(driver, contactTextArea);
        //Clear the field
        contactTextArea.clear();
        //Enter message
        contactTextArea.sendKeys(message);
        //enter Fullname
        Thread.sleep(1000);
        scrollintoviewElement(driver, fullName);
        fullName.clear();
        fullName.sendKeys(fullname);
        //enter email
        Thread.sleep(1000);
        scrollintoviewElement(driver, email);
        email.clear();
        email.sendKeys(mailID);
    }

    public void verifyFields() throws Exception {
        Thread.sleep(1000);
        scrollintoviewElement(driver, contactTextArea);
        Thread.sleep(1000);
        scrollintoviewElement(driver, fullName);
        Thread.sleep(1000);
        scrollintoviewElement(driver, email);
        Thread.sleep(1000);
        scrollintoviewElement(driver, confirm);
        Assert.assertTrue((contactTextArea.isDisplayed() && contactTextArea.isEnabled())
                        && (fullName.isDisplayed() && fullName.isEnabled())
                        && (email.isDisplayed() && email.isEnabled())
                        && (confirm.isDisplayed() && confirm.isEnabled()),
                "Text Area , Fullname ,email ID and Confirm checkBox");
    }

    public void clickConfirm() throws Exception {
        //Click confirm
        Thread.sleep(2000);
        scrollintoviewAndClickElement(driver, confirm);
    }

    public void clickSubmit() throws Exception {
        //Cick on submit button
        Thread.sleep(2000);
        scrollintoviewAndClickElement(driver, submit);
    }

    //Verify message 'Message Sent Successfully! '
    public Boolean verifyMessage(String message) throws Exception {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-400)", "");
        Thread.sleep(2000);
        explicitWaitClickable(driver,feedBack);
        scrollintoviewElement(driver, feedBack);
        return feedBack.getText().toUpperCase().trim().equals(message.toUpperCase().trim());
    }
}