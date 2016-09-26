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


public class ObjectMapTest {

	public WebDriver driver;
	private ObjectMap map;

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
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
		
	}

	@Test
	public void testBmiCalculator() {
		
		// Get the Object Map File
		map = new ObjectMap("./resources/files/object.properties");

		// Get the Height element
		WebElement height = driver.findElement(map.getLocator("height_field"));
		height.sendKeys("181");

		// Get the Weight element
		WebElement weight = driver.findElement(map.getLocator("weight_field"));
		weight.sendKeys("80");

		// Click on the Calculate button
		WebElement calculateButton = driver.findElement(map.getLocator("calculate_button"));
		calculateButton.click();

		// Verify the Bmi
		WebElement bmi = driver.findElement(map.getLocator("bmi_field"));
		Assert.assertEquals(bmi.getAttribute("value"),"24.4");
	}


	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
