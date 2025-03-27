package testBase;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseClass {
	public static WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setup() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();

		driver.get("https://www.spicejet.com/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
	}
	@AfterTest
	public static void refresh() {
//		driver.get("https://www.spicejet.com/");

//		driver.navigate().refresh();
	}

	@AfterClass
	public void teardown() throws InterruptedException {
//		System.out.println("TearDown");
		Thread.sleep(5000);
//		driver.findElement(By.xpath("//img[@src='https://www.spicejet.com/v1.svg']")).click();
		
		driver.quit();
	}

}
