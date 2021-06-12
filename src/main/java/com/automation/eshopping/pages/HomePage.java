package com.automation.eshopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;

	By homepageheader = By.xpath("/html/body/header/nav/div/a");

	public HomePage(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement homepageheader() {

		return driver.findElement(homepageheader);

	}

	public String getHomePageDashboardHeader() {

		return driver.findElement(homepageheader).getText();

	}

}