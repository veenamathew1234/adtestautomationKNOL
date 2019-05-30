package runner;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"classpath:Features","src/main/java/Features/ALogin.feature","src/main/java/Features/BWelcome.feature","src/main/java/Features/CJourney.feature"}
		,glue= {"stepDefinitions"},
		tags= {"~@ignore"},
		//monochrome =true,
		//plugin = { "pretty", "junit:target/cucumber-reports/Cucumber.xml" }
		plugin = {"pretty" , "html:Reports"}
		//plugin = {"junit:target/cucumber-reports/cucumber.xml"}
		)
public class ADRunner {
	
	
}
