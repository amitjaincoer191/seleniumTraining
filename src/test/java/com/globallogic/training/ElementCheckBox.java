package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementCheckBox {

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test 
	public void testCheckBox() throws InterruptedException { 
		
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,150)");
		
		// Get the CheckBox as WebElement using xpath
		WebElement gender= driver.findElement(By .xpath("//span[6]/input")); 
		
		// Check if its already selected? otherwise select the checkbox // by calling click() method 
		if (! gender.isSelected())  {
			gender.click();
			Thread.sleep(2000);
		}
		Assert.assertTrue(gender.isSelected());   // Verify Check box is selected 
	}


	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
