package seleniumScenarios;

import com.selenium.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class Scenario5 {

    @Test
    public static void runScenario(WebDriver driver, Properties p) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        String parent = driver.getWindowHandle();
        driver.manage().window().maximize();
        Thread.sleep(10000);

        // click the course registration button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[11]/div/div/a"))).click();
        Screenshot.takeScreenShot(driver, "S5/course_reg");
        Thread.sleep(15000);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(10000);

        // building a plan (Click on Plan Ahead)
        Screenshot.takeScreenShot(driver, "S5/banner");
        WebElement browseClassLink = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("planningLink"))));
        Thread.sleep(5000);
        browseClassLink.click();

        // Searching Semester to plan for
        Screenshot.takeScreenShot(driver, "S5/semester");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
        WebElement semSearch = driver.findElement(By.id("s2id_autogen1_search"));
        semSearch.sendKeys(p.getProperty("Semester"));
        Thread.sleep(2000);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        // Selecting Course
        Screenshot.takeScreenShot(driver, "S5/course");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"202330\"]")))).click();
        driver.findElement(By.id("term-go")).click();
        Thread.sleep(5000);

        // Creating plan
        Screenshot.takeScreenShot(driver, "S5/createPlan");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"createPlan\"]")))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_subject")))).click();
        WebElement subjectInput = driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]"));

        // Search Course program
        Screenshot.takeScreenShot(driver, "S5/course_search");
        subjectInput.sendKeys(p.getProperty("Course"));
        Thread.sleep(8000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"INFO\"]")))).click();
        Thread.sleep(5000);
        driver.findElement(By.id("search-go")).click();
        Thread.sleep(5000);

        // Adding the subject in the plan
        Screenshot.takeScreenShot(driver, "S5/subject_search");
        driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[5]/td[6]/div/button[2]")).click();
        driver.findElement(By.id("saveButton")).click();
        Thread.sleep(3000);

        // naming the plan
        Screenshot.takeScreenShot(driver, "S5/plan");
        driver.findElement(By.xpath("//*[@id=\"txt_planDesc\"]")).sendKeys(p.getProperty("planSummer"));
        Screenshot.takeScreenShot(driver, "S5/plan2");
        Thread.sleep(3000);
        Screenshot.takeScreenShot(driver, "S5/plan3");
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[32]/div[3]/div/button[2]")))).click();
//        Screenshot.takeScreenShot(driver, "S5/plan_created");
//        WebElement IrctcLogo = driver.findElement(By.xpath("/html/body/div[31]/div[3]/div/button[2]"));
//        Screenshot.takeScreenShot(driver, "S5/pla4");
//        String ans = IrctcLogo.getText();

//        String ExpectedText = "Save";
//        if (ans.equals(ExpectedText)) {
//            System.out.print("Success: Plan added! ");
//        } else {
//            System.out.print("Fail: Could not add plan! ");
//        }
//        Screenshot.takeScreenShot(driver, "S5/plan5");

        // saving the plan
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class=\"ui-dialog-buttonset\"]/button[contains(text(),'Save')]//parent::button")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"breadcrumbHeader\"]/span[4]")))).click();
        Thread.sleep(2000);
        Screenshot.takeScreenShot(driver, "S5/plan_created");
        Thread.sleep(10000);
        driver.close();
        driver.switchTo().window(parent);
        System.out.println("Successfully completed scenario 5");
    }
}

