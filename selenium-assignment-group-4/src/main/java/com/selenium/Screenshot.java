package com.selenium;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static void takeScreenShot(WebDriver driver, String fileName) {
		File src_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src_file, new File("./screenshots/" + "_" + fileName + "_" + ".png"));
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
	}
}