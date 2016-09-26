package com.globallogic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import static org.junit.Assert.*;

public class HomePage extends LoadableComponent<HomePage> {

	private WebDriver driver; 
	
	//static String url = "http://demo.magentocommerce.com/";
	static String url = "https://www.flipkart.com/";
	//private static String title = "Flipkart.com";

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	public void load() {
		Browser.open(url);
	}

	@Override
	public void isLoaded() {
		Assert.assertTrue(driver.getTitle().contains("Flipkart.com"));
	}

	public Search Search() {
		Search search = new Search(driver);
		return search;
	}
}
