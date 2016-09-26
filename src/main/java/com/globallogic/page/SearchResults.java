package com.globallogic.page;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.junit.Assert.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class SearchResults extends LoadableComponent<SearchResults> {

	private WebDriver driver;
	private String query;
	WebDriverWait wait;
	
	@FindBy(css="._1ZODb3")
	public WebElement noOfSearchResults;

	@FindBy(how = How.XPATH, using = "//div[@class='_3wU53n']")
	public List<WebElement> resultProductNames;
	
	public SearchResults(WebDriver driver, String query) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(this.driver,2000);
	}

	@Override
	public void isLoaded() {
		Assert.assertTrue(noOfSearchResults.getText().contains(this.query));
	}

    	
	public List<String> getProducts() {
		List<String> products = new ArrayList<String>();
		List<WebElement> productList = resultProductNames;
		
		for (WebElement item : productList)  {
			products.add(item.getText());
		}
		
		return products;
	}

	public void waitForSearchProductsToLoad() {		
	wait.until(ExpectedConditions.visibilityOfAllElements(resultProductNames));
	}
	
	public Search Search() {
		Search search = new Search(driver);
		return search;
	}

	@Override
	protected void load() {
	}
}

