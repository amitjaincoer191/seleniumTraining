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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SyncExplicitWaitCustomCondition {

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

	@Test
	public void testExplicitWaitTitleContains()	{
		WebElement query = driver.findElement(By.name("q"));
		query.sendKeys("selenium");
		WebElement searchButton = driver.findElement(By.name("btnk"));
		searchButton.click();

		//This will wait for 10 seconds for timeout before title is updated with search term
		//If title is updated in specified time limit test will move to the text step instead of waiting for 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("selenium"));

		//Verify Title
		Assert.assertTrue(driver.getTitle().toLowerCase().startsWith("selenium"));
	}



	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
