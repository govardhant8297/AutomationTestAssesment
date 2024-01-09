package com.automation.qa.testcases;

import static org.testng.Assert.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.automation.qa.base.TestBase;
import com.automation.qa.pages.LoginPage;

public class SauceDemoTestCases extends TestBase {

	public LoginPage loginPage;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		initialization();
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("validUsername"), prop.getProperty("validPassword"));
		Thread.sleep(3000);
		assertTrue(driver.getPageSource().contains("Swag Labs"));

	}

	@Test(priority = 1)
	public void validateLoginPageTitle() throws Exception {
		assertTrue(driver.getTitle().contains("Swag Labs"));
		System.out.println("Title verified successfully");

	}

	@Test(priority = 2)
	public void filetrList() throws Exception {
		System.out.println("Filter Oprions are: ");
		WebElement selectOptions = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select options = new Select(selectOptions);
		List<WebElement> list = options.getOptions();
		for (WebElement li : list) {
			System.out.println(li.getText());
		}
	}

	@Test(priority = 3)
	public void addToCartFirstProduct() throws Exception {
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		System.out.println("Click done on add to cart button on first item");
		driver.navigate().to("https://www.saucedemo.com/cart.html");
		System.out.println("Navigated to cart");
		System.out.println("Check the added item in cart");
		assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"));

	}

	@Test(priority = 4)
	public void allProductsNames() throws Exception {

		assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"));
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='inventory_item_label']//a"));
		Iterator<WebElement> itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().getText());
		}

	}

	@Test(priority = 5)
	public void allProductsNamesWithPricesInHashmap() throws Exception {

		List<WebElement> list_of_products = driver.findElements(By.xpath("//div[@class='inventory_item_label']//a"));
		List<WebElement> list_of_products_price = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

		// Use of HashMap to store Products with prices
		String product_name;
		String product_price;
		HashMap<String, String> map_final_products = new HashMap<String, String>();
		for (int i = 0; i < list_of_products.size(); i++) {
			product_name = list_of_products.get(i).getText();// Iterate and fetch product name
			product_price = list_of_products_price.get(i).getText();// Iterate and fetch product price
			product_price = product_price.replaceAll("[$,]", "");// Replace anything will space other than numbers
			map_final_products.put(product_price, product_name);// Add product and price in HashMap

		}
		Reporter.log("Product Name and price fetched from UI and saved in HashMap as:" + map_final_products.toString(),
				true);
	}

	@Test(priority = 6)
	public void validateCartItemsCountWithCountOfRemoveButtonsInProductsList() throws Exception {

		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		System.out.println("Click done on add to cart button on first 2 items");

		List<WebElement> eleAddToCartCount = driver.findElements(By.xpath("//*[starts-with(@id,'remove')]"));
		int actualAddedItemsCountInCart = eleAddToCartCount.size();

		System.out.println("Count of added items in cart: " + actualAddedItemsCountInCart);
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

		List<WebElement> cartItemsCount = driver
				.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']"));

		int CartItemsCount = cartItemsCount.size();

		if (CartItemsCount == actualAddedItemsCountInCart) {
			System.out.println("Added items count is matched with cart item count");
		} else {
			System.out.println("Added items count is not matched with cart item count");
		}
	}

	@Test(priority = 7)
	public void validateLogoutTest() throws Exception {

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Swag Labs"));
		driver.findElement(By.id("react-burger-menu-btn")).click();
		System.out.println("Click done on the menu");
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
		System.out.println("Logout of the application sucessfully done");

	}
	
	
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}