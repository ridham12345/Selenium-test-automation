package seleniumScenarios;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.*;

public class Scenario4 {

	@Test
	public static void runScenario(WebDriver driver, Properties p) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		Thread.sleep(3000);
		
		// Searching 'desk supplies' inside the search bar of the Bookstore site
		WebElement pen = driver.findElement(By.id("bned_site_search"));
		pen.sendKeys(p.getProperty("object1"));
		Screenshot.takeScreenShot(driver, "S4/object1");
		Thread.sleep(5000);
		pen.sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		Screenshot.takeScreenShot(driver, "S4/searching object");

		// clicking the first item from the search by it's id
		WebElement menuBTN = driver.findElement(By.xpath("/html/body/main/div[3]/div[5]/div[1]/div[2]/div/div/div/ul/div[1]/a"));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(menuBTN).build().perform();
		Thread.sleep(2000);
		menuBTN.click();
		Thread.sleep(5000);
		Screenshot.takeScreenShot(driver, "S4/object page");

		// click on the ad banner to remove it from the view before clicking the as it overlaps with 'Add to cart' button element
		WebElement adBanner =wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"extole-7076441836747516576\"]/div/div/a"))));
		adBanner.click();

		try{
			Thread.sleep(3000);
			WebElement add_to_cart=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"addToCartButton\"]"))));
			builder.moveToElement(add_to_cart).build().perform();
			Thread.sleep(3000);
			add_to_cart.click();
		}catch(Exception e) {
			Thread.sleep(3000);
			WebElement add_to_cart=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"addToCartButton\"]"))));
			builder.moveToElement(add_to_cart).click().perform();
			Thread.sleep(3000);
		}
		Thread.sleep(4000);
		
		// Viewing the cart from Bookstore
		Actions actions = new Actions(driver);
		WebElement main_menu = driver.findElement(By.xpath("//*[@id=\"headerDesktopView\"]/div[5]/div/ul/li[2]/div/div/button/a"));
		actions.moveToElement(main_menu);
		Screenshot.takeScreenShot(driver, "S4/cart");
		WebElement sub_menu = driver.findElement(By.xpath("//*[@id=\"headerDesktopView\"]/div[5]/div/ul/li[2]/div/div/button/a"));
		actions.moveToElement(sub_menu);
		actions.click().build().perform();
		Screenshot.takeScreenShot(driver, "S4/cart1");
		Thread.sleep(3000);
		System.out.println("Successfully completed scenario 4");
	}
}


