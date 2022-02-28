package stepDefs;


import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Properties;

public class LoginPageStepDef {

    private static String title;
    private ConfigReader configReader;
    Properties prop;
    private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());

    String correctUserName= "bilal345@gmail.com";
    String correctPassword = "bilal345";
    String incorrectUserName = "micheal";
    String incorrectPassword = "zxc";

    String failed1;
    String failed2;
    String failed3;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        //DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");


        configReader = new ConfigReader();
        prop = configReader.init_prop();
        DriverFactory.getDriver().get(prop.getProperty("loginPage"));
    }

    @Then("login page title should be {string}")
    public void loginPageTitleShouldBe(String expectedtitleName) {
        title= loginPage.getLoginPageTitle();
        Assert.assertTrue(title.contains(expectedtitleName));

    }

    @Then("forgot your password link should be present")
    public void forgot_your_password_link_should_be_present() {

        Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
    }




    @When("authorised user logs-in correctly")
    public void authorisedUserLogsInCorrectly() {
        loginPage.enterUserName(correctUserName);
        loginPage.enterPassword(correctPassword);
        loginPage.clickOnLoginButton();
    }


    @Then("authorised user can access his account")
    public void authorisedUserCanAccessHisAccount() {
        title= loginPage.getLoginPageTitle();
        Assert.assertTrue(title.contains("My account - My Store"));
    }





    @When("unauthorised user logs-in incorrectly")
    public void unauthorisedUserLogsInIncorrectly() {

        loginPage.enterUserName(correctUserName);
        loginPage.enterPassword(incorrectPassword);
        loginPage.clickOnLoginButton();
        failed1 = loginPage.authenticationFailed();
        System.out.println(failed1);

        loginPage.enterUserName(incorrectUserName);
        loginPage.enterPassword(correctPassword);
        loginPage.clickOnLoginButton();
        failed2 = loginPage.authenticationFailed();
        System.out.println(failed2);

        loginPage.enterUserName(incorrectUserName);
        loginPage.enterPassword(incorrectPassword);
        loginPage.clickOnLoginButton();
        failed3 = loginPage.authenticationFailed();
        System.out.println(failed3);


    }

    @Then("unauthorised user can not access his account")
    public void unauthorisedUserCanNotAccessHisAccount() {
        System.out.println("failed1; "+failed1);
        Assert.assertTrue(failed1.contains("Invalid password."));
        Assert.assertTrue(failed2.contains("Authentication failed."));
        Assert.assertTrue(failed3.contains("Authentication failed."));
    }


}
