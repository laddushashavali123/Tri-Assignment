package com.test.trivago.runner;

import com.test.trivago.listener.ExtentProperties;
import com.test.trivago.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * A sample test to demonstrate
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"com.test.trivago.stepdefinitions"},
    plugin = {"com.test.trivago.listener.ExtentCucumberFormatter:"}
)
public class RunCukesTest {
    @BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
//        extentProperties.setExtentXServerUrl("http://localhost:1337");
//        extentProperties.setProjectName("MyProject");
        extentProperties.setReportPath("output/myreportHomepageTrivagoUpdated.html");
    }

    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }

}
