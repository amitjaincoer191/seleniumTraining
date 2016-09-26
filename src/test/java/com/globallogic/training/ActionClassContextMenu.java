package com.globallogic.training;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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


public class ActionClassContextMenu {

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
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo/accesskeys.html"); 
	}

	@Test
	public void testContextMenu() throws InterruptedException {

		try {
			WebElement clickMeElement = driver.findElement(By.cssSelector(".context-menu-one.btn.btn-neutral"));
			WebElement editMenuItem = driver.findElement(By.xpath("(//ul//li//span)[1]"));
			Actions builder = new Actions(driver);
			builder.contextClick(clickMeElement).moveToElement(editMenuItem).click().perform();
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			Assert.assertEquals("clicked: edit", alert.getText());
			alert.dismiss();
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getAdditionalInformation());
		}
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}
