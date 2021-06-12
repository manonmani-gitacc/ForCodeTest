package com.automation.eshopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
   

    By homepageheader = By.xpath("/html/body/header/nav/div/a']");

    

    public HomePage(WebDriver driver){

        this.driver = driver;

    }

    //Get the User name from Home Page

        public String getHomePageDashboardHeader(){

         return    driver.findElement(homepageheader).getText();

        }
        
}