package com.pages;

import com.qa.util.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    BrowserUtils myBrowserUtils = new BrowserUtils();

    //By locators: OR(Object Repository)
    private By emailID= By.id("email");
    private By password= By.id("passwd");
    private By signInButton= By.id("SubmitLogin");
    private By forgorPwdLink=By.linkText("Forgot your password?");
    private By authenticationFailed = By.xpath("//div[@class='alert alert-danger']/ol/li");

    //Constructor
    public LoginPage(WebDriver driver) {

        this.driver=driver;
    }

    // page actions
    public String getLoginPageTitle() {

        return driver.getTitle();
    }

    public boolean isForgotPasswordLinkExists() {

        return driver.findElement(forgorPwdLink).isDisplayed();
    }

    public void enterUserName(String username) {
        myBrowserUtils.mySendKeysMethod(emailID, username);
        //driver.findElement(emailID).sendKeys(username);

    }

    public void enterPassword(String pwd) {
        myBrowserUtils.mySendKeysMethod(password, pwd);
        //driver.findElement(password).sendKeys(pwd);

    }

    public void clickOnLoginButton() {

        driver.findElement(signInButton).click();
    }

    public String authenticationFailed()  {

        String text =  driver.findElement(authenticationFailed).getText();

        return text;
    }
}
