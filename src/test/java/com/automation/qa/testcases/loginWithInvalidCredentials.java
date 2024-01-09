package com.automation.qa.testcases;

import org.testng.annotations.Test;
import com.automation.qa.base.TestBase;
import com.automation.qa.pages.LoginPage;
import org.testng.annotations.BeforeTest;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

public class loginWithInvalidCredentials extends TestBase{
	
	public LoginPage loginPage;
	
	@Test(priority = 8)
	public void validateLoginWithInvalidCredentials() throws Exception {
		
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("invalidUsername"), prop.getProperty("invalidPassword"));
		WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
		assertTrue(errorMessage.isDisplayed());
		System.out.println("Error message got for invalid credentials");

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		
		initialization();
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
