package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {

    private WebDriver driver;

    //By locators: OR(Object Repository)
    private By accountSection = By.xpath("//div[@class='col-xs-12 col-sm-6 col-lg-4']/ul/li");

    public AccountPage(WebDriver driver){

        this.driver=driver;
    }

   public String getAccountPageTitle(){

        return driver.getTitle();

   }

    public int accountSectionCount(){

        int count =driver.findElements(accountSection).size();
        return count;
    }

    public List<String> accountSectionList(){


        List<WebElement> accountElementList = driver.findElements(accountSection);
        List<String> accountTextList = new ArrayList<>();

        for (WebElement e : accountElementList ) {

            System.out.println("web elements list: " + e.getText());
            accountTextList.add(e.getText());

        }
        return accountTextList;

    }



}
