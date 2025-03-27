package testsCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class Check extends BaseClass{
	@Test
	public void test() {
		WebElement date=driver.findElement(By.xpath("//div[normalize-space()='Return Date']"));
		if(date.isEnabled()) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
	}
}
