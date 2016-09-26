package com.globallogic.training;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementState {

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

	@Test 
	public void testElementState()  { 

		WebElement checkBox = driver.findElement(By.xpath("//span[6]/input[1]"));
		// Check if its enabled before selecting it
		if (checkBox.isEnabled()) {
			// Check if its already selected? otherwise select the Checkbox
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		} else System.out.println("Gender Check Box is not enabled");

		WebElement disabledField = driver.findElement(By.name("lname"));
		// Check if its enabled before selecting it
		if (disabledField.isEnabled()) {
			// Check if its already selected? otherwise select the Checkbox
			if (!disabledField.isSelected()) {
				disabledField.click();
			}
		} else System.out.println("Field " + disabledField.getAttribute("name") + " is disabled");

		try {
			WebElement hiddenField = driver.findElement(By.xpath("//input[@type='hidden']"));
			// Check if its displayed before performing any operation
			if (hiddenField.isDisplayed()) { 
				// TO DO Action 
			} 
			else System.out.println("Field " + hiddenField.getAttribute("name") + " is hidden");

		} catch (ElementNotVisibleException e) {
			System.out.println(e.getAdditionalInformation());
		}
	}


	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
