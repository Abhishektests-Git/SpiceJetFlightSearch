package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResults extends BasePage{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	public SearchResults(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='list-results-section-0']") WebElement resultContainer;
	@FindBy(xpath="//img[@src='https://www.spicejet.com/v1.svg']") WebElement homePage;
	@FindBy(xpath="//div[@id='onward-flight-container']/div/div/div/div/div[4]") List<WebElement> FlightNameList;
	@FindBy(xpath="//div[@class='css-1dbjc4n r-1habvwh r-1777fci']") WebElement flightDetails;
	
	public void waitForSearchResults() {
		wait.until(ExpectedConditions.visibilityOf(resultContainer));
	}
	
	public void goHomePage() {
		homePage.click();
	}
//	public void noOfFlightsFound() {
//		System.out.println(flightDetails.getText());
//
//		System.out.println(FlightNameList.size()+" Flights found");
//		for(WebElement flight:FlightNameList) {
//			System.out.println(flight.getText());
//		}
//	}
	
	
}
