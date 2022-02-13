package parallel;


import com.pages.AccountPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Properties;

public class AccountPageStepDef {

    private ConfigReader configReader;
    Properties prop;
    private AccountPage accountPage=new AccountPage(DriverFactory.getDriver());

    @Given("user is on Accounts Page")
    public void userIsOnAccountsPage() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        DriverFactory.getDriver().get(prop.getProperty("accountPage"));
    }

    @Then("page title should be {string}")
    public void pageTitleShouldBe(String pageTitle) {

        Assert.assertTrue(accountPage.getAccountPageTitle().contains(pageTitle));;

    }

    @And("accounts section count should be {int}")
    public void accountsSectionCountShouldBe(int count) {

        Assert.assertTrue(accountPage.accountSectionCount()==count);
    }

    @Then("user gets accounts section")
    public void user_gets_accountss_section(DataTable sectionTable) {

        List<String> expectingTextTableList = sectionTable.asList();
        System.out.println("expectingTextTableList: " + expectingTextTableList);

        List<String> actualTextTableList = accountPage.accountSectionList();
        System.out.println("actualTextTableList: "+actualTextTableList);

        Assert.assertTrue(actualTextTableList.containsAll(expectingTextTableList));


    }


}
