package com.neelam.LearningSelenium;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerLogin {
	WebDriver wd;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromeDriver.exe");
		wd = new ChromeDriver();
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wd.manage().window().maximize();
		wait = new WebDriverWait(wd, 10);
	}

	@Test
	public void validateLogin() {
		WebElement myAccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='My Account']")));
		myAccount.click();
		WebElement login = wd.findElement(By.cssSelector("ul.dropdown-menu.dropdown-menu-right>li:nth-of-type(2)"));
		login.click();
		wd.getTitle();
		Assert.assertEquals(wd.getTitle(), "Account Login", "Login page not found");

		WebElement emailAddressInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input-email")));
		emailAddressInput.sendKeys("takkar@gmail.com");

		WebElement passwordInput = wd.findElement(By.id("input-password"));
		passwordInput.sendKeys("Password2");

		WebElement loginBtn = wd.findElement(By.cssSelector("input.btn.btn-primary"));
		loginBtn.click();
		wd.getTitle();
		Assert.assertEquals(wd.getTitle(), "My Account", "You are not on login page");
	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}
}
