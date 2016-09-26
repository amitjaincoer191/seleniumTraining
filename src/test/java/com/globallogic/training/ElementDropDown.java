package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementDropDown {

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

	public void testDropdown() throws InterruptedException { 

		Select make = new Select(driver.findElement(By.xpath("//select[1]")));
		// Verify Dropdown does not support multiple selection
		Assert.assertFalse(make.isMultiple()); 
		Assert.assertEquals(4, make.getOptions().size());
		make.selectByVisibleText("Volvo");
		Thread.sleep(2000);
		Assert.assertEquals("Volvo", make.getFirstSelectedOption().getText());
		make.selectByValue("audi");
		Thread.sleep(2000);
		make.selectByIndex(1);
		Assert.assertEquals("Saab", make.getFirstSelectedOption().getText());
		Thread.sleep(2000);
	}

	@Test
	public void testMultipleSelectList() throws InterruptedException {
		Select make = new Select(driver.findElement(By.xpath("//select[2]")));
		Assert.assertTrue(make.isMultiple());
		Assert.assertEquals(4, make.getOptions().size());
		make.selectByVisibleText("Volvo");
		Thread.sleep(2000);
		make.selectByVisibleText("Audi");
		Thread.sleep(2000);
		Assert.assertEquals("Volvo", make.getFirstSelectedOption().getText());
		make.deselectByValue("volvo");
		Thread.sleep(2000);
		Assert.assertEquals(1, make.getAllSelectedOptions().size());
		make.deselectByIndex(3);
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}


}
