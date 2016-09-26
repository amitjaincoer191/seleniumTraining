package com.globallogic.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.globallogic.page.*;

public class BmiCalculatorTests {

	private WebDriver driver;

	
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
	}

	@Test
	public void testBmiCalculation() {
	
		// Create an instance of Bmi Calculator Page class
		// and provide the driver
		BmiCalcPage bmiCalcPage = new BmiCalcPage(driver);

		// Open the Bmi Calculator Page
		bmiCalcPage.get();

		// Calculate the Bmi by supplying Height and Weight values
		bmiCalcPage.calculateBmi("181", "80");

		// Verify Bmi & Bmi Category values
		Assert.assertEquals(bmiCalcPage.getBmi(),"24.4");
		Assert.assertEquals(bmiCalcPage.getBmiCategory(),"Normal");
	}
	
	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
