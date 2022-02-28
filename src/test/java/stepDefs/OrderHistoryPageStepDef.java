package stepDefs;

import com.pages.OrderHistoryPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Properties;

public class OrderHistoryPageStepDef {

    private ConfigReader configReader;
    Properties prop;
    OrderHistoryPage orderHistoryPage=new OrderHistoryPage(DriverFactory.getDriver());

    @Given("user on the order history page")
    public void user_on_the_order_history_page() {

        configReader = new ConfigReader();
        prop=configReader.init_prop();
        DriverFactory.getDriver().get(prop.getProperty("orderHistoryPage"));

    }

    @Then("user validate land on the user history page")
    public void userValidateLandOnTheUserHistoryPage() {

        String expectedText = "Order history";
        String actualText = orderHistoryPage.getOrderPageNavigationText();
        Assert.assertTrue(expectedText.contains(actualText));
    }

    @Then("user validate without any purchase, order page message should be {string}")
    public void user_validate_without_any_purchase_order_page_message_should_be(String expectedMessage) {

        String actualMessage = orderHistoryPage.getOrderInfoText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }


}
