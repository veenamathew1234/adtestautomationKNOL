package runner;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features= "classpath:Features"
		,glue= {"stepDefinitions"},
		plugin = { "pretty", "junit:target/cucumber-reports/Cucumber.xml" }
		)
public class ADRunner {
	

}
