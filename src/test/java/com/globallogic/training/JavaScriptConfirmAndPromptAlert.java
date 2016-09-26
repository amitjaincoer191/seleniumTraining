package com.globallogic.training;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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


public class JavaScriptConfirmAndPromptAlert {

	public WebDriver driver;
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
		driver.get("file:///C:/Users/amit.ja/Desktop/training/Alerts.html");
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void testSimpleAlert() {
	    driver.findElement(By.id("simple")).click();
	    new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
	    Alert alert = driver.switchTo().alert(); // Get the Alert
	    String textOnAlert = alert.getText();  // Get the text displayed on Alert   
	    Assert.assertEquals("Hello! I am an alert box!", textOnAlert);     
	    alert.accept(); // Click OK button, by calling accept method
	  }

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
