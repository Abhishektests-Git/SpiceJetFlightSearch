package pageObject;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@Test
public class searchFlightPage extends BasePage {
//constructor
	public searchFlightPage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//	locator
	@FindBy(xpath = "//div[@data-testid='to-testID-origin']/div/div/input")
	WebElement fromCity;
	@FindBy(xpath = "//div[@data-testid='to-testID-destination']/div/div/input")
	WebElement destinationCity;
	@FindBy(xpath = "//div[normalize-space()='Departure Date']")
	WebElement depDate;
	@FindBy(xpath = "//div[normalize-space()='Return Date']")
	WebElement fromDate;
	@FindBy(xpath = "//div[normalize-space()='Passengers']")
	WebElement passangerCount;
	@FindBy(xpath = "//div[normalize-space()='Currency']")
	WebElement currencySelect;
	@FindBy(xpath = "//div[@data-testid='home-page-flight-cta']")
	WebElement searchBtn;
	@FindBy(xpath = "//div[contains(@data-testid,'undefined-month')]/div[1]")
	List<WebElement> allMonths;
	@FindBy(xpath = "//div[normalize-space()='Return Date']")
	WebElement datePicker;
	@FindBy(xpath = "//div[normalize-space()='Passengers']")
	WebElement personBtn;
	@FindBy(xpath = "//div[@data-testid='Adult-testID-plus-one-cta']")
	WebElement adultTab;
	@FindBy(xpath = "//div[@data-testid='Children-testID-plus-one-cta']")
	WebElement childTab;
	@FindBy(xpath = "//div[@data-testid='Infant-testID-plus-one-cta']")
	WebElement infantTab;
	@FindBy(xpath = "//div[@data-testid='home-page-travellers-done-cta']")
	WebElement doneBtn;
	@FindBy(xpath = "//div[@data-testid='home-page-flight-cta']")
	WebElement searchFlight;
	@FindBy(xpath = "//div[@data-testid='round-trip-radio-button']/div[1]//*[@cx='9']")
	List<WebElement> roundTripBtn;
//	actions

//	From city

	public void setFromCity(String frmCity) {
		fromCity.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@class='css-1dbjc4n r-19yat4t r-1rt2jqs']/div[2]/div")));
		List<WebElement> cityList = driver
				.findElements(By.xpath("//div[@class='css-1dbjc4n r-19yat4t r-1rt2jqs']/div[2]/div"));
		for (WebElement city : cityList) {
			if (city.getText().contains(frmCity)) {
				city.click();
				break;
			}
		}
	}

//	To city
	public void setToCity(String toCity) {
		destinationCity.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@class='css-1dbjc4n r-19yat4t r-1rt2jqs']/div[2]/div")));
		List<WebElement> cityList = driver
				.findElements(By.xpath("//div[@class='css-1dbjc4n r-19yat4t r-1rt2jqs']/div[2]/div"));
		for (WebElement city : cityList) {
			if (city.getText().contains(toCity)) {
				city.click();
				break;
			}
		}
	}

	public void setFromDate(String year, String month, String day) throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfAllElements(allMonths));

		WebElement nextBtn = driver.findElement(By.xpath("//div[@data-testid='undefined-calendar-picker']/div[1]"));
		List<String> allMonthsString = allMonths.stream().map(m -> m.getText()).collect(Collectors.toList());
		String monthAndYear = month + " " + year;
		String altMonthAndYear = month + "-" + year;
		int index = allMonthsString.indexOf(monthAndYear);
		for (int i = 0; i < index / 2; i++) {// div by 2 because one click changes 2 months
			if (!allMonthsString.get(i).equalsIgnoreCase(monthAndYear)) {
//				Thread.sleep(3000);
				nextBtn.click();
			}
		}
		Thread.sleep(1000);// waiting to get current month in exact place/finish animation
		String xpathDate = String.format("//div[contains(@data-testid,'%s')]//div[text()='%s']", altMonthAndYear, day);// clicking
																														// on
																														// exact
																														// day
																														// of
																														// the
																														// month
		driver.findElement(By.xpath(xpathDate)).click();
	}

	public void setToDate(String year, String month, String day) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(datePicker));
		if(roundTripBtn.size()>1)//if round trip btn is enabled.
		datePicker.click();
//		Thread.sleep(1000);
//System.out.println(roundTripBtn.size()>1);
		datePicker.click();

		wait.until(ExpectedConditions.visibilityOfAllElements(allMonths));
		WebElement nextBtn = driver.findElement(By.xpath("//div[@data-testid='undefined-calendar-picker']/div[1]"));
		List<String> allMonthsString = allMonths.stream().map(m -> m.getText()).collect(Collectors.toList());
		String monthAndYear = month + " " + year;
		String altMonthAndYear = month + "-" + year;
		int index = allMonthsString.indexOf(monthAndYear);
		String revMonth = "//div[@data-testid=\"undefined-calendar-picker\"]/div[2]";
		WebElement revmonthBtn = driver.findElement(By.xpath(revMonth));
		for (int i = 0; i < (index); i++) {// reseting the month card as start month is selected as departure month by
											// default'
			revmonthBtn.click();

		}
		for (int i = 0; i < index / 2; i++) {
			if (!allMonthsString.get(i).equalsIgnoreCase(monthAndYear)) {
//				Thread.sleep(3000);
				nextBtn.click();
			}
		}
		Thread.sleep(1000);// waiting to get current month in exact place/finish animation
		String xpathDate = String.format("//div[contains(@data-testid,'%s')]//div[text()='%s']", altMonthAndYear, day);// clicking
																														// on
																														// exact
																														// day
																														// of
																														// the
																														// month
		driver.findElement(By.xpath(xpathDate)).click();

	}

	public void setPessangerCount(String adult, String child, String infant) {

//		WebElement personBtn = driver.findElement(By.xpath("//div[normalize-space()='Passengers']"));

		personBtn.click();

//		WebElement adultTab = driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']"));
//		WebElement childTab = driver.findElement(By.xpath("//div[@data-testid='Children-testID-plus-one-cta']"));
//		WebElement infantTab = driver.findElement(By.xpath("//div[@data-testid='Infant-testID-plus-one-cta']"));
//		WebElement doneBtn = driver.findElement(By.xpath("//div[@data-testid='home-page-travellers-done-cta']"));

		for (int i = 1; i < Integer.parseInt(adult); i++) {
//			wait.until(ExpectedConditions.visibilityOf(adultTab));

			adultTab.click();
		}
		for (int i = 0; i < Integer.parseInt(child); i++) {
//			wait.until(ExpectedConditions.visibilityOf(childTab));
			childTab.click();
		}
		for (int i = 0; i < Integer.parseInt(infant); i++) {
//			wait.until(ExpectedConditions.visibilityOf(infantTab));

			infantTab.click();
		}
//		doneBtn.click();
	}

	public void searchFlight() {
		searchFlight.click();
	}

}
