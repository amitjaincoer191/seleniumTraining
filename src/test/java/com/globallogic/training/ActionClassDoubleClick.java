package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ActionClassDoubleClick {

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
		driver.get("file:///C:/Users/amit.ja/Desktop/training/DoubleClickDemo.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test 
	public void testDoubleClick() throws InterruptedException {
		WebElement message = driver.findElement(By.id("message"));
	 	 // Verify color is Blue
		Assert.assertEquals(message.getCssValue("background-color"),"rgba(0, 0, 255, 1)");
	  	 Actions builder = new Actions(driver);
		  builder.doubleClick(message).perform();
		  Thread.sleep(4000);
	  	 // Verify Color is Yellow
		  Assert.assertEquals(message.getCssValue("background-color"),"rgba(255, 255, 0, 1)");
	}
	
	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
