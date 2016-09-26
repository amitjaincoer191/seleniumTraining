package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IEBrowser {

	public  WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception { 
		
		// Create a new instance of the InternetExplorer driver
		System.setProperty("webdriver.ie.driver","resources\\browserdrivers\\IEDriverServer.exe");
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		driver = new InternetExplorerDriver(capability);
	}

	@Test
	public void testIEBrowser() throws InterruptedException{
		driver.get("file:///C:/Users/amit.ja/Desktop/training/BMIApplication.html");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebElement heightField = driver.findElement(By.name("heightCMS"));
		heightField.clear();
		heightField.sendKeys("155");

		WebElement weightField = driver.findElement(By.name("weightKg"));
		weightField.clear();
		weightField.sendKeys("70");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();

		String bmi = driver.findElement(By.id("bmi")).getAttribute("value");
		String category = driver.findElement(By.id("bmi_category")).getAttribute("value");
        
		Assert.assertEquals(bmi, "29.1");
		Assert.assertEquals(category, "Overweight");
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
