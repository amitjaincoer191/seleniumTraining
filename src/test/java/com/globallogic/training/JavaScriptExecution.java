package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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


public class JavaScriptExecution {

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
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@Test 
	public void testJavaScript() throws InterruptedException {

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String title = (String) js.executeScript("return document.title");
	    System.out.println(" Page title : " + title);
	    long links = (Long) js.executeScript("var links = document.getElementsByTagName('a'); return links.length");
	    System.out.println(" No of links : " + links);
	    String linkHref = (String) js.executeScript("var linkValue = document.getElementsByTagName('a')[0].getAttribute('href'); return linkValue");
	    System.out.println(" First link href attribute : " + linkHref);
	    js.executeScript("document.getElementById('userId').value = arguments[0]","Myuname");
	    Thread.sleep(3000);
	}
	
	
	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
