package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementAttributeANDCSSValue {

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
	public void elementAttributeAndCssValue() throws InterruptedException {
		driver.get("file:///C:/Users/amit.ja/Desktop/training/htmlFile 2.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement message = driver.findElement(By.id("message")); 
        Assert.assertEquals(message.getAttribute("justify"),"align");
        
        message = driver.findElement(By.id("messageBox"));
        String width = message.getCssValue("width");
    	Assert.assertEquals(width,"200px");
    	
    	String color= message.getCssValue("background-color");
    	Assert.assertEquals(color,"rgba(0, 128, 0, 1)");

	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
