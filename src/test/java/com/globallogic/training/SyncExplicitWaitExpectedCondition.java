package com.globallogic.training;

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
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;


public class SyncExplicitWaitExpectedCondition {

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
		driver.get("http://www.google.com");
	}

	public void testExplicitWaitCustomConditionUsingPredicate()	{
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Oliver Twist");
		element.submit();
		// Google's search is rendered dynamically with JavaScript. 
		// Wait for the page t o load, timeout after 10 seconds
		Predicate<WebDriver> pred = new Predicate<WebDriver>() {
			public boolean apply(WebDriver d) {
				return d.getTitle().startsWith("Oliver");
			}
		};
		
		new WebDriverWait(driver,10).until(pred);
		
		/*
		 * SHORT FORM
		 * new WebDriverWait(driver, 10).until(new Predicate<WebDriver>(){
			public boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("Oliver");
			}
		}
				);*/
		Assert.assertEquals(driver.getTitle(),"Oliver Twist - Google Search");
	}

	@Test
	public void testExplicitWaitCustomConditionUsingFunction()	{
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Oliver Twist");
		element.submit();
		
		Function<WebDriver, Boolean> f1 = new Function<WebDriver, Boolean>() {  
			public Boolean apply(WebDriver arg0) { 
				return arg0.getTitle().startsWith("Oliver"); 
			}  
		};
				
		new WebDriverWait(driver, 50).until(f1);
		Assert.assertEquals(driver.getTitle(),"Oliver Twist - Google Search");
	}
	
	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
