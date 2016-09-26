package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
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


public class ActionClassDragAndDrop {

	public WebDriver driver;
	By draggableElement = By.id("draggable");
	By droppableElement = By.id("droppable");

	
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
		driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(draggableElement));

	}

	@Test 
	public void testDragDrop() throws InterruptedException {
		
		WebElement source = driver.findElement(draggableElement);
		WebElement target = driver.findElement(droppableElement);
		Thread.sleep(6000);
		Actions builder = new Actions(driver);
		builder.dragAndDrop(source, target).perform();
		Assert.assertEquals(target.getText(),"Dropped!");
	}   

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
