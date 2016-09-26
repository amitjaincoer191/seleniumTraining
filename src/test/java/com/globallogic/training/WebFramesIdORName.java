package com.globallogic.training;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
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

import com.google.common.base.Function;


public class WebFramesIdORName {

	public WebDriver driver;
	WebDriverWait wait;
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
		driver.get("file:///C:/Users/amit.ja/Desktop/training/Frames.html");
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void testFrameWithIdOrName() {    
		// Activate the frame on left side using it's id attribute
		driver.switchTo().frame("left");
		WebElement msg = driver.findElement(By.tagName("p"));
		Assert.assertEquals(msg.getText(),"This is Left Frame");
		
		// Activate the Page, this will move context from frame back to the Page
		driver.switchTo().defaultContent();      
		
		// Activate the frame on right side using it's name attribute
		driver.switchTo().frame("right");
		msg = driver.findElement(By.tagName("p"));
		Assert.assertEquals(msg.getText(),"This is Right Frame");
		
		driver.switchTo().defaultContent();
		
		// Activate the frame in middle using it's index. Index starts at 0
		driver.switchTo().frame(1);
		msg = driver.findElement(By.tagName("p"));
		driver.switchTo().defaultContent();
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
