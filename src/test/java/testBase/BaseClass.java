package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	WebDriverWait wait;
	public Logger logger;

	@BeforeClass
	@Parameters({"OS","browser"})
	public void setup(String OS,String browser) throws InterruptedException, IOException {
//		configure properties file
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		Properties prop=new Properties();
		prop.load(file);
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Browser not exists");
				return;

			}
		}
		
		
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("appURL"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		setting up logger
		logger=LogManager.getLogger(this.getClass());
		
		
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
	
	public String captureScreen(String tname) throws IOException {
//		Date date=new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//		making source file/real screenshot file
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

//		naming the screenshot file  
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

//		making the screenshot file
		File targetFile = new File(targetFilePath);

//		copy source file to target file
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}


}
