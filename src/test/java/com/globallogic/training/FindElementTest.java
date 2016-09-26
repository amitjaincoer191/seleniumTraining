package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindElementTest {

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
	public void testFindElementMethod() {
		driver.get("file:///C:/Users/amit.ja/Desktop/training/htmlFile 2.html");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		WebElement username; 
		WebElement password;

		try {		username= driver.findElement(By.id("userId"));
		password= driver.findElement(By.id("password"));

		username = driver.findElement(By.name("usernameA"));
		password = driver.findElement(By.name("password"));

		username = driver.findElement(By.className("usrFieldClassA"));
		password = driver.findElement(By.className("pwdFieldClassA"));
		}
		catch (NoSuchElementException e){
			System.out.println("NoSuchElementException " + e.getAdditionalInformation());
		}
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
