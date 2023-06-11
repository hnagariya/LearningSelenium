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

public class CustomerChangePassword {
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
	public void validateChangePassword() {
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
		
		WebElement changeYourPassword = wd.findElement(By.xpath("//a[text()='Change your password']"));
		changeYourPassword.click();
		
		String getTitleOfPage = wd.getTitle();
		Assert.assertEquals(getTitleOfPage, "Change Password", "You are not on change password page");
		
		WebElement passwordInputForChangePassword = wd.findElement(By.id("input-password"));
		passwordInputForChangePassword.sendKeys("Password2");
        WebElement confirmPasswordInput = wd.findElement(By.id("input-confirm"));
		confirmPasswordInput.sendKeys("Password2");
		WebElement continueBtn = wd.findElement(By.cssSelector("input.btn.btn-primary"));
		continueBtn.click();
	    WebElement successfulChangePassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=' Success: Your password has been successfully updated.']")));
	    String changePasswordSuccessful=successfulChangePassword.getText();
	    Assert.assertEquals(changePasswordSuccessful,"Success: Your password has been successfully updated.","Password change unsuccessful");
		
		WebElement logOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Logout'])[2]")));
		logOut.click();
		Assert.assertEquals(wd.getTitle(), "Account Logout", "Logout unsuccessful");
		WebElement continueBtnAfterLogOut = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Continue']")));
		continueBtnAfterLogOut.click();
		
		WebElement myAccountAfterLogOut = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='My Account']")));
		myAccountAfterLogOut.click();
		WebElement loginAfterLogOut = wd.findElement(By.cssSelector("ul.dropdown-menu.dropdown-menu-right>li:nth-of-type(2)"));
		loginAfterLogOut.click();
		wd.getTitle();
		Assert.assertEquals(wd.getTitle(), "Account Login", "Login page not found");

		WebElement emailAddressInputAfterChangedPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input-email")));
		emailAddressInputAfterChangedPassword.sendKeys("takkar@gmail.com");
        WebElement passwordInputAfterChangePassword = wd.findElement(By.id("input-password"));
		passwordInputAfterChangePassword.sendKeys("Password2");
        WebElement loginBtnAfterChangePassword = wd.findElement(By.cssSelector("input.btn.btn-primary"));
		loginBtnAfterChangePassword.click();
		wd.getTitle();
		Assert.assertEquals(wd.getTitle(), "My Account", "You are not on login page");
	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}
}
