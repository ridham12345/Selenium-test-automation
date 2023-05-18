package seleniumScenarios;

import com.selenium.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scenario1 {

    @Test
    public static void runScenario(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(5000);

        // select a category to choose menu options to be added in Favorites
        Screenshot.takeScreenShot(driver, "S1/main_student_hub");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]/div[2]/div/div[1]/div/p"))).click();

        // add the action to favorites
        WebElement addBTN1 = driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[1]/div/i"));
        Actions build = new Actions(driver);
        build.moveToElement(addBTN1).build().perform();
        Thread.sleep(2000);
        addBTN1.click();

        // add the second action to favorites
        WebElement addBTN2 = driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[2]/div/i"));
        Actions build2 = new Actions(driver);
        build2.moveToElement(addBTN2).build().perform();
        Thread.sleep(2000);
        Screenshot.takeScreenShot(driver, "S1/second_favourite");
        addBTN2.click();
        Thread.sleep(2500);

        Screenshot.takeScreenShot(driver, "S1/favourites");
        System.out.println("Successfully completed scenario 1");
    }
}
