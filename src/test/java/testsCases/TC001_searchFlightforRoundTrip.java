package testsCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObject.SearchResults;
import pageObject.searchFlightPage;
import testBase.BaseClass;
import utility.DataProviders;

public class TC001_searchFlightforRoundTrip extends BaseClass {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, priority = 1)
	public void roundTripTicket(String fromCity, String toCity, String startYear, String startMonth, String startDay,
			String returnYear, String returnMonth, String returnDay, String adult, String children, String infant)
			throws InterruptedException {
		searchFlightPage FBP = new searchFlightPage(driver);
		SearchResults searchResults = new SearchResults(driver);
//		Thread.sleep(1000);
		FBP.setFromCity(fromCity);
////		Thread.sleep(3000);
		FBP.setToCity(toCity);
////		Thread.sleep(2000);
		FBP.setFromDate(startYear, startMonth, startDay);
		Thread.sleep(1000);
		;
		FBP.setToDate(returnYear, returnMonth, returnDay);
		FBP.setPessangerCount(adult, children, infant);
		Thread.sleep(1000);
		FBP.searchFlight();
		searchResults.waitForSearchResults();
		searchResults.noOfFlightsFound();
		Thread.sleep(3000);
		searchResults.goHomePage();
		// driver.close();
//		Thread.sleep(5000);
//		driver.quit();

	}
}
