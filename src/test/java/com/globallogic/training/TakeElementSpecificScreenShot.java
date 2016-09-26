package com.globallogic.training;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TakeElementSpecificScreenShot {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "./resources/browserdrivers/chromedriver.exe");

		// Launch Chrome
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(caps);
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/amit.ja/Desktop/training/htmlFile 2.html");

	}

	@Test
	public void testcaptureElementSpecificScreenShot() throws IOException {
		WebElement table= driver.findElement(By .xpath("//span[2]"));
		FileUtils.copyFile(captureElementSpecificScreenShot(table), new File("./resources/files/element_specific_screenshot.png"));
	}

	public File captureElementSpecificScreenShot(WebElement element) throws IOException{

		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Create an instance of Buffered Image from captured screenshot
		BufferedImage img = ImageIO.read(screen);

		// Get the Width and Height of the WebElement using getSize()
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		// Create a rectangle using Width and Height
		Rectangle rect = new Rectangle(width, height);

		// Get the Location of WebElement in a Point.
		// This will provide X & Y co-ordinates of the WebElement
		Point p = element.getLocation();

		// Create image by for element using its location and size.
		// This will give image data specific to the WebElement
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,rect.height);

		// Write back the image data for element in File object
		ImageIO.write(dest, "png", screen);

		// Return the File object containing image data
		return screen;
	}



	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
