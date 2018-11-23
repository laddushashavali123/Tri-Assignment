package com.test.trivago.pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseDriver {
    public static final String SCREENSHOT = System.getProperty("user.dir") + "\\screenshots";
    public static final String PROPERTIES = System.getProperty("user.dir") + "\\src\\test\\resources\\testProperties.properties";
    public static final String BROWSER_PROP = "browser";
    public static final String URL = "url";
    public static Properties props = new Properties();

    protected static WebDriver driver;

    public BaseDriver() {
        //init properties
        initProperties();
        //getBrowser property
        setDriver(BROWSER_PROP);
    }

    //open the browser
    public static WebDriver getwebdriver(String browserprop) {
        WebDriver driver;
        if (browserprop.toUpperCase().trim().equals("CHROME")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("disable-extensions");
            opt.addArguments("--start-maximized");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
            driver = new ChromeDriver(capabilities);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
            return driver;
        } else if (browserprop.toUpperCase().trim().equals("firefox".toUpperCase().trim())) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(false);
            profile.setAssumeUntrustedCertificateIssuer(true);
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("--log", "error");
            capabilities.setCapability("moz:firefoxOptions", options);
            // capabilities.setCapability("marionette", true);
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            driver = new FirefoxDriver(capabilities);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           /* driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);*/
            return driver;
        }
            else
            return null;
    }

    //get the Properties
    private static String getProperty(String key) {
        return props.getProperty(key).toString();
    }

    /**
     * Browser properties initialization
     */
    private static void initProperties() {
        try {
            props.load(new FileReader(PROPERTIES));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (props.getProperty(BROWSER_PROP).toUpperCase().trim().equals("chrome".toUpperCase().trim())) {
            // using testProperties.properties
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        }else
        if (props.getProperty(BROWSER_PROP).toUpperCase().trim().equals("firefox".toUpperCase().trim())) {
            // using testProperties.properties
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
        }
    }

    public static void setDriver(String browser) {
        driver = getwebdriver(getProperty(browser));
        driver.get(getProperty(URL));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    //Fluentwait
    public static void fluentWait(WebDriver webDriver, WebElement webElement) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(10, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);

    }

    //implicit wait
    public static void implicitWait(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    //Explicit wait
    public static WebElement explicitWait(WebDriver webDriver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        webElement = wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    //Explicit wait
    public static WebElement explicitWaitClickable(WebDriver webDriver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    //Scroll into view
    public static void scrollintoviewElement(WebDriver webdriver, WebElement webElement) throws Exception {
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(500);
        hightlightElement(webdriver, webElement);
    }

    //Scroll into view
    public static void scrollintoviewAndClickElement(WebDriver webdriver, WebElement webElement) throws Exception {
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(500);
        //Highlight Element
        hightlightElement(webdriver, webElement);
        //Click on Element
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].click();", webElement);
        implicitWait(driver);
    }
    //Highlight element
    public static void hightlightElement(WebDriver webdriver, WebElement webElement) throws InterruptedException {
        // draw a border around the found element
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor) webdriver;
        jse.executeScript("arguments[0].style.border='3px solid red'", webElement);
        Thread.sleep(500);
        jse.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
        Thread.sleep(500);

    }

    //Scroll to bottom of the page
    public static void scrollToBottom(WebDriver webDriver) {

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
    }

    public static String captureScreen(WebDriver webDriver) {
        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(webDriver);
            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "./screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }
    public static boolean scroll_Page(WebElement webelement, int scrollPoints)
    {
        try
        {
            Actions dragger = new Actions(driver);
            // drag downwards
            int numberOfPixelsToDragTheScrollbarDown = 10;
            for (int i = 10; i < scrollPoints; i = i + numberOfPixelsToDragTheScrollbarDown)
            {
                dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(webelement).build().perform();
            }
            Thread.sleep(500);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static void switchWindow(WebDriver driver){
        //Switch to new window
        Set<String> handles= driver.getWindowHandles();
        System.out.println(handles);
        // Pass a window handle to the other window
        for (String handle : driver.getWindowHandles()) {
            System.out.println(handle);
            driver.switchTo().window(handle);
        }
    }
}
