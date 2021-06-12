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

import com.automation.eshopping.pages.HomePage;

public class NewTest {

	WebDriver driver;

	String subprodcutbeingselected = "";

	String totalvalueduringcheckout = "";
	HomePage homepage ;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tjaya\\fRAMEOWKR\\lib\\newlib\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		//NOt a better way to call like this,it has to be form configuraiton file
		//Due to time constraint ,taking directly for time being
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

	
	
	//Function to get random int for random selection from the list of products for operation 
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
		
			
		
			Reporter.log("Step 1----Getting the dynamic list of main product cateogory available");
						// As the wwebpage is dynamic is it better to get dynamic lsitof products and do
						// operation rather than hard coding
	
						String productname = getrandonintforselection(listofmainproductslabel);
				
						String xpath = "///*[contains(@id, '" + productname + "')]";
	
			Reporter.log("Step 2----Selection of main product category to proceed with");
	
						driver.findElement(By.xpath(xpath)).click();
				
						Reporter.log("Step 3----Selection of sub product category to proceed with");
				
						// Thought of random selection here as well but due to time constraint proceeding
						// with first list item if available
				
						java.util.List<WebElement> subproducts = driver.findElements(By.xpath("//*[contains(@class, 'imgProduct')]"));
				
						if (subproducts.size() > 0) {
				
							subprodcutbeingselected = driver.findElement(By.xpath("(//*[contains(@class, 'imgProduct')])[1]"))
									.getText();
							driver.findElement(By.xpath("(//*[contains(@class, 'imgProduct')])[1]")).click();
				
						}
	
			Reporter.log("Step 3----Selection of sub product category and proceeding for checkout");
	
						// Click add to cart
						driver.findElement(By.xpath("//button[contains(@name, 'save_to_cart')]")).click();
				
						// Click checkout
				
						driver.findElement(By.xpath("(//*[contains(@id, 'checkOutPopUp')])[2]")).click();
				
						totalvalueduringcheckout = driver.findElement(By.xpath("//*[@id=\"userCart\"]/div[2]/label[2]/span")).getText();
				
						Reporter.log("Step 4----Checkout as user already exists");
				
						String pageheader = driver.findElement(By.xpath("/html/body/div[3]/section/article/h3")).getText();
				
						assertTrue(pageheader.contains("ORDER PAYMENT"), "Application landed successfully ");
				
						driver.findElement(By.xpath("//input[contains(@name, 'usernameInOrderPayment')]")).sendKeys("testuser_123");
				
						driver.findElement(By.xpath("//input[contains(@name, 'usernameInOrderPayment')]")).sendKeys("Test@123");
				
						driver.findElement(By.xpath("//*[contains(@id, 'next_btn')]")).click();
	
			Reporter.log("Step 4----Checkout as user already exists");
	
						pageheader = driver.findElement(By.xpath("/html/body/div[3]/section/article/h3")).getText();
				
						assertTrue(pageheader.contains("ORDER METHOD"), "User is in ORDER PAYMENT ");
				
						// verif for payment method display
				
						String headertext = driver.findElement(By.xpath("//*[@id='detailslink']/label[2]")).getText();
				
						assertTrue(pageheader.contains("PAYMENT METHOD"), "User is in  PAYMENT METHOD");
				
			Reporter.log("Step 5---choose payment and order");
	
						driver.findElement(By.xpath("//input[contains(@name, 'safepay_username')]")).sendKeys("testuser_123");
				
						driver.findElement(By.xpath("//input[contains(@name, 'safepay_password')]")).sendKeys("Test@123");
				
						driver.findElement(By.xpath("//*[contains(@id, 'pay_now_btn_SAFEPAY')]")).click();
	
			Reporter.log("Step 5---Verification of order summary page");
	
						headertext = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/h2/span")).getText();
				
						assertTrue(pageheader.contains("Thank you for buying with Advantage"),
								"ORDER SUMARY contains -->Thank you message");
				
						String ordertrackigntext = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/p")).getText();
				
						assertTrue(ordertrackigntext.contains("Your tracking number is"),
								"ORDER SUMARY contains -->ORDER TRACKING INFO");
				
						String usernameinfo = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/div/div[1]/div/div[1]/label"))
								.getText();
				
						assertTrue(usernameinfo.contains("testuser"), "ORDER SUMARY contains -->USERINFO");
				
						String userino = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/p")).getText();
				
						assertTrue(ordertrackigntext.contains("Your tracking number is"),
								"ORDER SUMARY contains -->ORDER TRACKING INFO");
				
						String totalvalueduring_ordersummary = driver
								.findElement(By.xpath("//*[@id='orderPaymentSuccess']/div/div[3]/div[3]/label/a")).getText();
				
						assertTrue(totalvalueduringcheckout.contains(totalvalueduring_ordersummary),
								"ORDER SUMARY toatl value matches with during checkout");
		 
	}

	// Test case to verify applciaiton lainch
	//This test case can be made as dependent as if applcaitn fails it wont run
	@Test
	public void verifyapplication_upstatus() {
			//Direct call for demo , rather than taking from config file
		
				driver.get("http://http://advantageonlineshopping.com/#/");
				
			//Taken assumptions are website is up and running which is done by taking network tab contents and check for staus code
				int statuscode=200;
				assertTrue((statuscode==200||statuscode==304),
						"Applicaiton is up and running for use");
				
				homepage = new HomePage(driver);
				
				assertTrue(homepage.getHomePageDashboardHeader().contains("dvantage"),
						"Application Header displayed to user ");
	}
 
	// Test case to register user

	// Test case to modify order

}
