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


public class WebMultipleWindows {

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
		driver.get("file:///C:/Users/amit.ja/Desktop/training/Config.html");
		wait = new WebDriverWait(driver, 20);
	}

	
	public void testWindowUsingTitle()  {
		String parentWindowId = driver.getWindowHandle();
		driver.findElement(By.id("visitbutton")).click();
		// Get Handles of all the open windows and iterate through list 
		try {  
			for (String windowId : driver.getWindowHandles()) {
				String title = driver.switchTo().window(windowId).getTitle();
				if (title.equals("Visit Us")) {
					System.out.println("Message inside \"Visit Us\" window : "
							 + driver.findElement(By.tagName("p")).getText());
					driver.close(); // Close the Visit Us window
					break;
				} 
			}   
		}  finally { 
			driver.switchTo().window(parentWindowId);
			Assert.assertEquals(driver.getTitle(),"Build my Car - Configuration");
		   } 
	} 

	
	@Test
	public void testWindowUsingName() {
		
		String parentWindowId = driver.getWindowHandle();
		driver.findElement(By.id("helpbutton")).click();

		try {
			driver.switchTo().window("HelpWindow");
			System.out.println(driver.findElement(By.tagName("a")).getAttribute("href"));
			wait.until(ExpectedConditions.titleContains("hELp Window"));
			try {
				  Assert.assertEquals(driver.getTitle(),"hELp Window");
			} finally {
				driver.close();
			}
		} finally {
			driver.switchTo().window(parentWindowId);
		}
		Assert.assertEquals("Build my Car - Configuration", driver.getTitle());
	}
	
	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
