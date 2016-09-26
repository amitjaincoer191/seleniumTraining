package com.globallogic.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementTextANDXPath {

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
	public void elementTextAndXpath() throws InterruptedException {

		driver.get("file:///C:/Users/amit.ja/Desktop/training/htmlFile 2.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement spanText= driver.findElement(By.xpath("//span[1]")); 
	    String spanTagText = spanText.getText();
	    System.out.println("xpath(\"//span[1]\") :- " + spanTagText);
	    
        WebElement div1Text= driver.findElement(By.xpath("//span[1]//div[1]")); 
	    String div1TagText = div1Text.getText();
	    System.out.println("xpath(\"//span[1]//div[1]\") :- " + div1TagText);
	    
	    WebElement div2Text= driver.findElement(By.xpath("//span[1]//div[2]")); 
		String div2TagText = div2Text.getText(); 
		System.out.println("xpath(\"//span[1]//div[1]\") :- " + div2TagText);

	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}
}

