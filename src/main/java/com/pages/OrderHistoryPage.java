package com.pages;

import com.qa.util.BrowserUtils;
import com.qa.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHistoryPage {

    BrowserUtils browserUtils=new BrowserUtils();

    private WebDriver driver;

        //By locators: OR(Object Repository)
        private By orderInfoText = By.xpath("//p[contains(text(),'You have not placed any orders.')]");
        private By orderNavigateText = By.xpath("//span[contains(text(),'Order history')]");


       //constructor
       public OrderHistoryPage(WebDriver driver){
           this.driver=driver;
       }

       public String getOrderInfoText(){

           return browserUtils.getSingleElementText(orderInfoText);
       }

       public String getOrderPageNavigationText(){

           return browserUtils.getSingleElementText(orderNavigateText);

       }

}
