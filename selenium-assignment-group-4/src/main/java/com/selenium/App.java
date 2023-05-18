package com.selenium;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumScenarios.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {

    static ExtentTest test;
    static ExtentReports report;

    public static void main(String[] args) throws Exception {

        report = new ExtentReports("/Users/ridhammangukiya/Desktop/Northeastern University/Semester 3/INFO 6155 Quality Assurance/selenium-assignment-group-4/testResults.html", true);

        try (InputStream input = new FileInputStream("./config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            System.setProperty("webdriver.chrome.driver", "/Users/ridhammangukiya/Desktop/Northeastern University/Semester 3/INFO 6155 Quality Assurance/selenium-assignment-group-4/chromedriver");
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();

            // Perform Login to https://me.northeastern.edu
            performLogin(webDriver, prop);
            Thread.sleep(10000);

            // Start all the Scenarios

            // Scenario 1
            try {
                test = report.startTest("1st Scenario");
                Scenario1.runScenario(webDriver);
                test.log(LogStatus.PASS, "Add options in My Favorites: PASS");
            } catch (Exception exeption) {
                test.log(LogStatus.FAIL, exeption);
            }

            // Scenario 2
            try {
                test = report.startTest("2nd Scenario");
                Scenario2.runScenario(webDriver);
                test.log(LogStatus.PASS, "Delete the option from My Favorites: PASS");
            } catch (Exception exeption) {
                test.log(LogStatus.FAIL, exeption);
            }

            // Scenario 3
            try {
                test = report.startTest("3rd Scenario");
                Scenario3.runScenario(webDriver, prop);
                test.log(LogStatus.PASS, "Browse classes for the Spring 2023 Semester: PASS");
            } catch (Exception exeption) {
                test.log(LogStatus.FAIL, exeption);
            }

            // Scenario 5
            try {
                test = report.startTest("5th Scenario");
                Scenario5.runScenario(webDriver, prop);
                test.log(LogStatus.PASS, "Create a plan for course registration: PASS");
            } catch (Exception exeption) {
                test.log(LogStatus.FAIL, exeption);
            }

            Thread.sleep(5000);

            // Scenario 4
            try {
                test = report.startTest("4th Scenario");
                performLoginWebStore(webDriver, prop);
                Scenario4.runScenario(webDriver, prop);
                test.log(LogStatus.PASS, "Add the items to your cart in the NU Bookstore: PASS");
            } catch (Exception exeption) {
                test.log(LogStatus.FAIL, exeption);
            }

            //Scenario End

            // Report end
            report.endTest(test);
            report.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // @Before
    public static void performLogin(WebDriver webDriver, Properties prop) throws Exception {

        // Go to Login page
        webDriver.get(prop.getProperty("MYNEU_LINK"));
        webDriver.findElement(By.className("largeTextNoWrap")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);

        // Filing the form with appropriate details
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        user.sendKeys(prop.getProperty("NEU_USERNAME"));
        WebElement pass = webDriver.findElement(By.id("password"));
        pass.sendKeys(prop.getProperty("NEU_PASSWORD"));
        Screenshot.takeScreenShot(webDriver, "Login/login_info");
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();
        webDriver.switchTo().frame("duo_iframe");

        // Duo authentication
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"auth_methods\"]/fieldset[1]/div[1]/button"))).click();
        Screenshot.takeScreenShot(webDriver, "Login/duo");
        Thread.sleep(15000);

        // Microsoft sign in page
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.className("win-button")))).click();

        Thread.sleep(8000);

        //
        webDriver.get("https://northeastern.sharepoint.com/sites/studenthub/SitePages/Student-Resources.aspx#/resources");


//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]/span/i"))).click();
//
//        // Click 'Resources button' under me.northeasterb.edu page
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"spSiteHeader\"]/div/div[2]/div/div[3]/div/div/div/span[4]/a"))).click();
        Thread.sleep(5000);

    }

    // Opening up bookstore link
    @Before
    public static void performLoginWebStore(WebDriver webDriver, Properties prop) throws Exception {

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        Actions actions = new Actions(webDriver);

        // opening bookstore link
        webDriver.get(prop.getProperty("Bookstore_link"));
        String actual = webDriver.getTitle();
        Screenshot.takeScreenShot(webDriver, "bookstore/web_bookstore_link");

        // Searching the appropriate link
        String expected = "Apparel, Gifts & Textbooks | Northeastern University Official Bookstore";
        Assert.assertEquals("Website title does not match", expected, actual);
        webDriver.switchTo().defaultContent();

    }
}
