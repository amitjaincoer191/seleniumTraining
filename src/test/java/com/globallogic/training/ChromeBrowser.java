package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeBrowser {

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
