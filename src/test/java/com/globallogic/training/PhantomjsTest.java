package com.globallogic.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PhantomjsTest  {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"C:\\Users\\amit.ja\\Desktop\\android\\testWorkSpace\\training\\resources\\browserdrivers\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
		
	}	
	
	@Test
	public void testBmiCalc() {
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
		WebElement height = driver.findElement(By.name("heightCMS"));
		height.sendKeys("181");

		WebElement weight = driver.findElement(By.name("weightKg"));
		weight.sendKeys("80");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();

		WebElement bmi = driver.findElement(By.id("bmi"));
		System.out.println(bmi.getAttribute("value"));
		Assert.assertEquals(bmi.getAttribute("value"), "24.4");

		WebElement bmiCategory = driver.findElement(By.name("bmi_category"));
		System.out.println(bmiCategory.getAttribute("value"));
		Assert.assertEquals(bmiCategory.getAttribute("value"), "Normal");
	}

	@AfterTest
	public void tearDown(){
		if (driver != null)
			driver.close();
	}

}