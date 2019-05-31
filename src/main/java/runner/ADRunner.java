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
		monochrome =true,
		tags="~@ignore",
		//plugin = { "pretty", "junit:target/cucumber-reports/Cucumber.xml" }
		plugin = {"pretty" , "html:Reports"}
		)
public class ADRunner {
	

}
