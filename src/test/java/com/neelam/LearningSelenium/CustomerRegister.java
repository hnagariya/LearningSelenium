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

public class CustomerRegister {
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
	public void validateRegisterWithRightInput() {
		WebElement myAccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='My Account']")));
		myAccount.click();
		WebElement register = wd.findElement(By.xpath("//a[text()='Register']"));
		register.click();
		WebElement firstNameInput = wd.findElement(By.id("input-firstname"));
		firstNameInput.sendKeys("Tony");
		WebElement lastNameInput = wd.findElement(By.id("input-lastname"));
		lastNameInput.sendKeys("Takkar");
		WebElement emailInput = wd.findElement(By.id("input-email"));
		emailInput.sendKeys("takkar@gmail.com");
		WebElement telephoneInput = wd.findElement(By.id("input-telephone"));
		telephoneInput.sendKeys("4567890123");
		WebElement passwordInput = wd.findElement(By.id("input-password"));
		passwordInput.sendKeys("Password2");
		WebElement passwordConfirmInput = wd.findElement(By.id("input-confirm"));
		passwordConfirmInput.sendKeys("Password2");
		WebElement checkBoxAgree = wd.findElement(By.cssSelector("input[name='agree']"));
		checkBoxAgree.click();
		WebElement continueBtn = wd.findElement(By.cssSelector("input[value='Continue']"));
		continueBtn.click();
		wd.getTitle();
		Assert.assertEquals(wd.getTitle(), "Your Account Has Been Created!", "You are not registered");
	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}

}