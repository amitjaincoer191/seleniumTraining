package com.globallogic.training;

import java.util.List;
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


public class ActionClassWithKeyBoard {

	public WebDriver driver;
	By tableLinkText= By.linkText("Table");
	By rowSelectionLinkText = By.linkText("Row Selection");
	By radioButtonMultiple = By.xpath("//*[@id='iceform:icepnltabset:0:multipleSelection:_2']");
	By selectedRows = By.xpath("//div[@class='icePnlGrp exampleBox']/table[@class='iceDatTbl']/tbody/tr");
	List<WebElement> rows;
	
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
		driver.get("http://component-showcase.icesoft.org/component-showcase/showcase.iface");
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void testRowSelectionUsingControlKey() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableLinkText));
		driver.findElement(tableLinkText).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(rowSelectionLinkText));
		driver.findElement(rowSelectionLinkText).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(radioButtonMultiple));
		driver.findElement(radioButtonMultiple).click();
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[@class='iceDatTbl']/tbody/tr"));
		//Select second and fourth row from table using Control Key.Row Index start at 0
		Actions builder = new Actions(driver);  
		builder.click(tableRows.get(1))
		.keyDown(Keys.CONTROL)
		.click(tableRows.get(3))
		.keyUp(Keys.CONTROL)
		.build()
		.perform();
		//Verify Selected Row table shows two rows selected
	
		try {
			Function<WebDriver, Boolean> f1 = new Function<WebDriver, Boolean>() {  
				public Boolean apply(WebDriver d) {
					rows = d.findElements(selectedRows);
					if (rows.size()>1)
					return true;
					else return false;
				}  
			};
			new WebDriverWait(driver, 10).until(f1);
		} catch (TimeoutException e) {
			System.out.println(e.getAdditionalInformation());
		}
		
		Assert.assertEquals(rows.size(),2);
	}


	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
