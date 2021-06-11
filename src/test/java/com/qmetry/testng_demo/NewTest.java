package com.qmetry.testng_demo;

import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {

	WebDriver driver;

	String subprodcutbeingselected = "";

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tjaya\\fRAMEOWKR\\lib\\newlib\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://http://advantageonlineshopping.com/#/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	ArrayList<String> listofmainproductslabel;

	// As the wwebpage is dynamic is it better to get dynamic lsitof products and do
	// operation rather than hard coding
	public void getlistofproducst() {
		listofmainproductslabel = new ArrayList<String>();
		String xpathforcategory = " //* [contains (@aria-label, 'CategoryTxt')]";

		java.util.List<WebElement> mainproducts = driver.findElements(By.xpath("xpathforcategory"));

		if (mainproducts.size() > 0) {
			for (WebElement webElement : mainproducts) {

				listofmainproductslabel.add(webElement.getText());

			}
		}

		Reporter.log("Main products category availabel are ..." + listofmainproductslabel.toString());
	}

	public String getrandonintforselection(ArrayList<String> list) {
		int max = 0;
		int min = 0;
		int randomint = 0;
		if (list.size() > 0) {
			max = list.size() - 1;
			min = 0;

			double rand = Math.random();
			randomint = (int) (rand * ((max - min) + 1)) + min;
		}
		String randomproduct = list.get(randomint);
		return randomproduct;
	}

	@Test
	public void orderandcheckout_happypath_e2e() {

		Reporter.log("----Test case execution for happy path - e2e testing------");

		Reporter.log("Step 1----Getting the list of main product cateogory available");

		String productname = getrandonintforselection(listofmainproductslabel);

		String xpath = "///*[contains(@id, '" + productname + "')]";

		Reporter.log("Step 2----Selection of main product category to proceed with");

		driver.findElement(By.xpath(xpath)).click();

		Reporter.log("Step 3----Selection of sub product category to proceed with");

		// Though of random selection here as well but due to time constraint proceeding
		// with list item if available

		java.util.List<WebElement> subproducts = driver.findElements(By.xpath("//*[contains(@class, 'imgProduct')]"));

		if (subproducts.size() > 0) {

			subprodcutbeingselected = driver.findElement(By.xpath("(//*[contains(@class, 'imgProduct')])[1]"))
					.getText();
			driver.findElement(By.xpath("(//*[contains(@class, 'imgProduct')])[1]")).click();

		}
		
		

		Reporter.log("Step 3----Selection of sub product category and proceeding for checkout");
		
		//Click add to cart
		driver.findElement(By.xpath("//button[contains(@name, 'save_to_cart')]")).click();
		
		//Click checkout
		
		driver.findElement(By.xpath("(//*[contains(@id, 'checkOutPopUp')])[2]")).click();


		Reporter.log("Step 4----Checkout as user already exists");
		
		
        String pageheader = driver.findElement(By.xpath("/html/body/div[3]/section/article/h3")).getText();
        
         
        
        assertTrue(pageheader.contains("ORDER PAYMENT"),"Application landed successfully ");
		
        		
        driver.findElement(By.xpath("//input[contains(@name, 'usernameInOrderPayment')]")).sendKeys("testuser_123");

		driver.findElement(By.xpath("//input[contains(@name, 'usernameInOrderPayment')]")).sendKeys("Test@123");

		

		driver.findElement(By.xpath("//*[contains(@id, 'next_btn')]")).click();
		
		

		// verif for payment method display

		String headertext = driver.findElement(By.xpath("//*[@id=\"detailslink\"]/label[2]")).getText();

		driver.findElement(By.xpath("//input[contains(@name, 'safepay_username')]")).sendKeys("testuser_123");

		driver.findElement(By.xpath("//input[contains(@name, 'safepay_password')]")).sendKeys("Test@123");

		driver.findElement(By.xpath("//*[contains(@id, 'pay_now_btn_SAFEPAY')]")).click();

		headertext = driver.findElement(By.xpath("//*[@id=\"orderPaymentSuccess\"]/h2/span")).getText();

		// Thank you for buying with Advantage

		String ath = "//*[@id=\"orderPaymentSuccess\"]/p";

		// Your tracking number is

		// username //*[@id="orderPaymentSuccess"]/div/div[1]/div/div[1]/label

		// tiatol =//*[@id="orderPaymentSuccess"]/div/div[3]/div[3]/label/a
	}

	// Test case to veirfy applciaiton lainch

	// Test case to place order and checkout = Happy Path

	// Test case to register user

	// Test case to modify order

}
