package com.globallogic.page;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {

	private WebDriver driver;
	
	@FindBy(css=".LM6RPg")
	private WebElement searchTextBox;
	
	@FindBy(css = ".vh79eN")
	private WebElement searchButton;
	
	public Search(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public SearchResults searchInStore(String query) {
		searchTextBox.sendKeys(query + Keys.SPACE);
		searchButton.click();
		return new SearchResults(driver, query);
	}
}
