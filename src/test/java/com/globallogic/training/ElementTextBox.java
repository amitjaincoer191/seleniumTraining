package com.globallogic.training;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementTextBox {

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
	public void testTextBox() throws InterruptedException {
		driver.get("http://www.google.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement element =  driver.findElement(By.name("q"));
		element.sendKeys("Any Char Sequence");
		element.clear();
		element.sendKeys("123" + Keys.BACK_SPACE);		 
		driver.findElement(By.name("btnG"));
		element.click();
		Thread.sleep(5000);
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}
}
