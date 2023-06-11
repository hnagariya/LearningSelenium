package com.neelam.LearningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumIntro {
	WebDriver wd;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromeDriver.exe");
		wd = new ChromeDriver();
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		}

	@Test
	public void validateLogin() {
		WebElement emailInput=wd.findElement(By.id("input-email"));
		WebElement passwordInput=wd.findElement(By.id("input-password"));
		WebElement loginBtn=wd.findElement(By.cssSelector("input[value='Login']"));
		
		emailInput.sendKeys("tony@email.com");
		passwordInput.sendKeys("Password1");
		loginBtn.click();
		wd.getTitle();
		Assert.assertEquals(wd.getTitle(),"My Account");
	}
	@Test
	public void validateIsRadioButtonSelected() {
		WebElement registerContinueButton=wd.findElement(By.xpath("//a[text()='Continue']"));	
		registerContinueButton.click();
		WebElement radioButtonNo=wd.findElement(By.xpath("( //input[@name = 'newsletter'])[2]"));
		boolean isRadioButtonNoSelected=radioButtonNo.isSelected();
		Assert.assertTrue(isRadioButtonNoSelected);
	}
	
	@Test
	public void validateEmailLabelText() {
		WebElement continueButton= wd.findElement(By.xpath("//label[text()='E-Mail Address']"));
		String emailText=continueButton.getText();
		Assert.assertEquals(emailText, "E-Mail Address","email label text not correct");
		
	}
	@AfterMethod
	public void tearDown() {
		wd.close();
	}
	
}