package com.globallogic.training;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class ElementWebTable {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "resources\\browserdrivers\\chromedriver.exe");

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
	public void testWebTable()  { 
		WebElement simpleTable = driver.findElement(By.xpath("//span[2]")); 
		// Get all rows 
		List<WebElement> rows = simpleTable.findElements(By.tagName("tr")); 
		Assert.assertEquals(rows.size(),4); 

		// Print data from each row 
		for (WebElement row : rows) { 
		    List<WebElement> cols = row.findElements(By.tagName("td")); 
		    for (WebElement col : cols) {
				 System.out.print(col.getText() + "\t"); 
			   } System.out.println(); 
		   }
		}


	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
