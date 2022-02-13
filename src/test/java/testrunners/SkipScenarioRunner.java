package testrunners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/",
                "rerun:target/failedrerun.txt" // for list our failed test on this file
        },
        tags = "not @Skip", //we can add tags for ignore with not
        monochrome = true,
        glue = { "parallel" },
        features = { "src/test/resources/parallel" }
)
public class SkipScenarioRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

// if we want skip any scenario with maven console test we can use
// this command --> mvn test -DCucumber.options="--tags '@Login and not @Skip'"
// with this command test include @Login tags but @Skip tags
