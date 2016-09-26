package com.globallogic.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.globallogic.page.Browser;
import com.globallogic.page.HomePage;
import com.globallogic.page.SearchResults;

public class SearchTest {

	@Test
	public void testProductSearch() {
		try {
			// Create an instance of Home page
			HomePage homePage = new HomePage(Browser.driver());

			// Navigate to the Home page
			homePage.get();

			// Search for 'phones', the searchInStore method will return SerchResults
			SearchResults searchResult = homePage.Search().searchInStore("iphone 5s");
			
			searchResult.waitForSearchProductsToLoad();

//			System.out.println(" Total Product Search Results " + searchResult.noOfSearchResults.getText());
//			System.out.println(" Name of first searched product " + searchResult.getProducts().get(0));
			
			
			Assert.assertTrue(searchResult.getProducts().size()>0);
			Assert.assertTrue(searchResult.getProducts().get(0).contains("Apple iPhone"));
			
			
			
		} finally {
 			Browser.close();
		}
	}
}
