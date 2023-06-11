package com.neelam.LearningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment1 {
	WebDriver wd;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromeDriver.exe");
		wd = new ChromeDriver();
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wait = new WebDriverWait(wd, 10);
	}

	@Test
	public void validateSubmitContactForm() {
		WebElement contactUs = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Contact Us']")));
		contactUs.click();
		Assert.assertEquals(wd.getTitle(), "Contact Us");
		WebElement yourName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='form-control'])[1]")));
		yourName.sendKeys("Neelam");

		WebElement emailAddress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email']")));
		emailAddress.sendKeys("neelam123@gmail.com");
		
		WebElement enquiry = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-enquiry")));
		enquiry.sendKeys("Namastey Naveen");
		
	//	String yourNameInput = yourName.getText();
	//	System.out.println(yourNameInput);
	//	String emailAddressInput = emailAddress.getText();
	//	System.out.println(emailAddressInput);
	//	String enquiryInput = enquiry.getText();

		//Assert.assertEquals(yourNameInput, "Neelam", "Neelam is not as atext in your name field");
		//Assert.assertEquals(emailAddressInput, "Neelam123@gmail.com",
		//		"email Address not matched or not as a text");
	//	Assert.assertEquals(enquiryInput, "Namastey Naveen", "Incorrect match");

		WebElement submitBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Submit']")));
		submitBtn.click();

		WebElement messageAfterSuccessfulSubmit = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#content>p")));
		String s = messageAfterSuccessfulSubmit.getText();
		Assert.assertEquals(s, "Your enquiry has been successfully sent to the store owner!","Form has not been submitted");

	}

	@AfterTest
	public void tearOut() {
		wd.close();
	}

}
