package seleniumScenarios;

import com.selenium.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scenario2 {

    @Test
    public static void runScenario(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(5000);

        // click the action button again to remove from favorites
        WebElement removeBTN1 = driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[1]/div/i"));
        Actions build1 = new Actions(driver);
        build1.moveToElement(removeBTN1).build().perform();
        Thread.sleep(2000);
        removeBTN1.click();

        // click the secon action button again to remove from favorites
        WebElement removeBTN2 = driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[2]/div/i"));
        Actions build = new Actions(driver);
        build.moveToElement(removeBTN2).build().perform();
        Thread.sleep(2000);
        Screenshot.takeScreenShot(driver, "S2/after_remove");
        removeBTN2.click();
        Thread.sleep(2500);

        Screenshot.takeScreenShot(driver, "S2/after_remove_link");
        System.out.println("Successfully completed scenario 2");
    }
}
