package com.globallogic.training;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class WebElementExtender {

	public static WebDriver driver;
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
		driver.get("file:///C:/Users/amit.ja/Desktop/training/htmlfile 2.html");
		wait = new WebDriverWait(driver, 20);
	}

	@Test
	public void testWebElementExtenderSetandHighlightAttribute() throws InterruptedException {
		By pwdElement = By.id("password");
		By uname1Element = By.id("userId");
		
		wait.until(ExpectedConditions.elementToBeClickable(pwdElement));
		WebElement unameElement = driver.findElement(uname1Element);
		highlightElement(unameElement);
		setAttribute(unameElement, "value", "unameforTest");
		Thread.sleep(1000);
		
		WebElement passwordElement = driver.findElement(pwdElement);
		highlightElement(passwordElement);
		setAttribute(passwordElement, "value", "testPwd");
		Thread.sleep(1000);		
	} 
	
	public void setAttribute(WebElement element, String attributeName, String value)  {
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
	}
	
	public static void highlightElement(WebElement element) throws InterruptedException {

	    JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
	    jsDriver.executeScript("arguments[0].setAttribute('style', arguments[1]);", 
	    		element,"border: 4px solid orange;");
	    Thread.sleep(1000);
	    jsDriver.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, "");
	}
	
	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
