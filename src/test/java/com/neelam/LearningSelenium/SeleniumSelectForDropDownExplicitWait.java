package com.neelam.LearningSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumSelectForDropDownExplicitWait {
	WebDriver wd;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromeDriver.exe");
		wd = new ChromeDriver();

		wd.get("https://www.webroot.com/us/en/cart?key=206636B2-ACE5-4D15-B267-16EC7E08A593");
		wd.manage().window().maximize();
		wait = new WebDriverWait(wd, 20);
		WebElement acceptCookie = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button#onetrust-accept-btn-handler")));
				
		acceptCookie.click();
	}

	@Test
	public void validateSelectCountrySelected() {
		WebElement selectCountryField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name = 'billing.country']")));
		Select sc = new Select(selectCountryField);

		sc.selectByIndex(4);
		Assert.assertEquals(sc.getFirstSelectedOption().getText(), "United Kingdom");

		sc.selectByValue("IN");
		Assert.assertEquals(sc.getFirstSelectedOption().getText(), "India", "India not selected");

		sc.selectByVisibleText("China");
		Assert.assertEquals(sc.getFirstSelectedOption().getText(), "China", "China not selected");
	}

	
	public void sleep() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}

}