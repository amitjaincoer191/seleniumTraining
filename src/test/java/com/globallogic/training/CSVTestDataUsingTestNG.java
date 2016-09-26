package com.globallogic.training;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

public class CSVTestDataUsingTestNG {

	private static WebDriver driver;
	private static StringBuffer verificationErrors = new StringBuffer();

	@DataProvider
	public static Object[][] testData() throws IOException {

		List<String[]> csvDetails = getTestData("resources\\files\\data.csv");

		Object[][] objArray = new Object[csvDetails.size()][];
		for(int i=0; i< csvDetails.size(); i++){
			String temp[] = csvDetails.get(i);
			Object a[]= new Object[temp.length];
			for(int j=0;j<temp.length;j++){
				a[j]=temp[j];
			}
			objArray[i]=a;
			//System.out.println(objArray[i][0]+" "+objArray[i][1]+ " "+objArray[i][2]+" "+objArray[i][3]);
		}
		return objArray;
	}


	public static List<String[]> getTestData(String fileName) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		List<String[]> myEntries = reader.readAll();
		reader.close();
		return myEntries;
	}

	@BeforeTest
	public static void setUp() throws Exception { 
		System.setProperty("webdriver.chrome.driver","resources\\browserdrivers\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--start-maximized");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(caps);

		driver.get("http://dl.dropbox.com/u/55228056/bmicalculator.html");
	}

	@Test(dataProvider = "testData")
	public void testBMICalculator(String height, String weight, String bmi,
			String category) throws Exception {
		try {
			WebElement heightField = driver.findElement(By.name("heightCMS"));
			heightField.clear();
			heightField.sendKeys(height);

			WebElement weightField = driver.findElement(By.name("weightKg"));
			weightField.clear();
			weightField.sendKeys(weight);

			WebElement calculateButton = driver.findElement(By.id("Calculate"));
			calculateButton.click();

			WebElement bmiLabel = driver.findElement(By.name("bmi"));
			assertEquals(bmi, bmiLabel.getAttribute("value"));

			WebElement bmiCategoryLabel = driver.findElement(By.name("bmi_category"));
			assertEquals(category,bmiCategoryLabel.getAttribute("value"));

		} catch (Error e) {
			//Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			System.err.println("Assertion Fail " + verificationErrors.toString());
		}
	}

	@AfterTest
	public static void tearDown() throws Exception {
		//Close the browser
		driver.quit();
	}
}