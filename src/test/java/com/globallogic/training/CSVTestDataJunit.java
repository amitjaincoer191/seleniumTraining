package com.globallogic.training;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

import com.opencsv.CSVReader;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

@RunWith(value = Parameterized.class)
public class CSVTestDataJunit {
  
  private static WebDriver driver;
  private static StringBuffer verificationErrors = new StringBuffer();

  private String height;
  private String weight;
  private String bmi;
  private String bmiCategory;
  
  @Parameters
  public static List<String[]> testData() throws IOException {
	  return  getTestData("resources\\files\\data.csv");
  }
  
  //Add the constructor which maps the instance variables with the test data
  public CSVTestDataJunit(String height, String weight, String bmi, String bmiCategory) {
    this.height = height;
    this.weight=weight;
    this.bmi = bmi;
    this.bmiCategory = bmiCategory;
  }
  
  public static List<String[]> getTestData(String fileName) throws IOException {
	  CSVReader reader = new CSVReader(new FileReader(fileName));
	    List<String[]> myEntries = reader.readAll();
	    reader.close();
	    return myEntries;
	}
  
  @BeforeClass
  public static void setUp() throws Exception { 
    // Create a new instance of the Firefox driver
	  System.setProperty("webdriver.chrome.driver","resources\\browserdrivers\\chromedriver.exe");
	  ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
    
    driver.get("http://dl.dropbox.com/u/55228056/bmicalculator.html");
  }

  @Test
  public void testBMICalculator() throws Exception {
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
      assertEquals(bmiCategory,bmiCategoryLabel.getAttribute("value"));
      
    } catch (Error e) {
      //Capture and append Exceptions/Errors
      verificationErrors.append(e.toString());
      System.err.println("Assertion Fail " + verificationErrors.toString());
    }
  }

  @AfterClass
  public static void tearDown() throws Exception {
    //Close the browser
    driver.quit();
  }
}