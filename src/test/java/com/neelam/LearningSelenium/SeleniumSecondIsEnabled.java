package com.neelam.LearningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumSecondIsEnabled {
	WebDriver wd;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromeDriver.exe");
		wd = new ChromeDriver();
		wd.get("https://ca.hotels.com/login?&uurl=e3id%3Dredr%26rurl%3D%2F%3Flocale%3Den_CA%26pos%3DHCOM_CA%26siteid%3D300000002");
		}

	@Test
	public void validateButtonIsEnabled() {
		WebElement continueButton= wd.findElement(By.id("loginFormSubmitButton"));
		boolean isContinueButtonEnabled=continueButton.isEnabled();
		Assert.assertFalse(isContinueButtonEnabled,"Continue buton is enabled");
		WebElement emailInput=wd.findElement(By.id("loginFormEmailInput"));
		emailInput.sendKeys("adm@gmail.com");
		isContinueButtonEnabled= continueButton.isEnabled();
		Assert.assertTrue(isContinueButtonEnabled,"continue button is not enabled after enterring email");
	
	}
	
	@AfterMethod
	public void tearDown() {
		wd.close();
	}
	
}