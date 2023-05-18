package seleniumScenarios;

import com.selenium.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class Scenario3 {

    @Test
    public static void runScenario(WebDriver driver, Properties p) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        String parent = driver.getWindowHandle();

        // Maximize the browser window
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // clicking on Course Registration
        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[11]/div/div/a")).click();

        // handles newly opened window tab
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(15000);

        // click browse class button
        WebElement browse_class_link = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("classSearchLink"))));
        Screenshot.takeScreenShot(driver, "S3/course_reg_category");
        Thread.sleep(5000);
        browse_class_link.click();

        // filling Search with Spring Full Semester
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
        WebElement semester_search = driver.findElement(By.id("s2id_autogen1_search"));
        semester_search.sendKeys(p.getProperty("Semester"));
        Screenshot.takeScreenShot(driver, "S3/searchSemester");
        Thread.sleep(2000);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"202330\"]")))).click();
        driver.findElement(By.id("term-go")).click();
        Thread.sleep(2000);

        // Course Input
        Screenshot.takeScreenShot(driver, "S3/semesterInput");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_subject")))).click();
        WebElement course_input = driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]"));
        course_input.sendKeys(p.getProperty("Course"));
        Thread.sleep(9000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"INFO\"]")))).click();
        driver.findElement(By.id("search-go")).click();
        Screenshot.takeScreenShot(driver, "S3/course_browse");
        Thread.sleep(20000);
        driver.close();
        driver.switchTo().window(parent);
        System.out.println("Successfully completed scenario 3");

    }
}
