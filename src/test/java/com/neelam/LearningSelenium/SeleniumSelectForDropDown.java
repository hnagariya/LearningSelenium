package com.neelam.LearningSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumSelectForDropDown {
	WebDriver wd;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromeDriver.exe");
		wd = new ChromeDriver();

		wd.get("https://www.webroot.com/us/en/cart?key=206636B2-ACE5-4D15-B267-16EC7E08A593");
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//sleep();
		WebElement acceptCookie = wd.findElement(By.id("onetrust-accept-btn-handler"));
		acceptCookie.click();
	}

	@Test
	public void validateSelectCountrySelected() {

		WebElement selectCountryField = wd.findElement(By.cssSelector("select[name = 'billing.country']"));
		Select sc = new Select(selectCountryField);
		//sleep();
		sc.selectByIndex(4);
		Assert.assertEquals(sc.getFirstSelectedOption().getText(), "United Kingdom");
		//sleep();
		sc.selectByValue("IN");
		Assert.assertEquals(sc.getFirstSelectedOption().getText(), "India", "India not selected");
		//sleep();
		sc.selectByVisibleText("China");
		Assert.assertEquals(sc.getFirstSelectedOption().getText(), "China", "China not selected");
	}

	public void sleep() {
		try {
			Thread.sleep(5000);
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