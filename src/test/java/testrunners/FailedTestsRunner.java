package testrunners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

// if any scenario is failed ve can re run with testng rerun plugin
// before that we must use plain runner class also
@CucumberOptions(
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/",
                "rerun:target/failedrerun.txt"
        },
        monochrome = true,
        glue = {"stepDefs"},
        features = { "@target/failedrerun.txt" }
)


public class FailedTestsRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
