package runner;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"classpath:Features","src/main/java/Features/ALogin.feature","src/main/java/Features/BDemographics.feature","src/main/java/Features/CWelcome.feature","src/main/java/Features/DJourney.feature"}
		//,tags= {"~@ignore"},
		//monochrome =true,
		,glue= {"stepDefinitions"},plugin= {"json:target/cucumber-reports/cucumber.json"}
		)

public class ADRunnerTest {

}
