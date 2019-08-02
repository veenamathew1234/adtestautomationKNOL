package runner;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import utils.CommonMethods;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"classpath:Features","src/main/java/Features/ALogin.feature","src/main/java/Features/BDemographics.feature","src/main/java/Features/CWelcome.feature","src/main/java/Features/DJourney.feature"}
		//,tags= {"~@ignore"},
		,glue= {"stepDefinitions"}, 
				//monochrome =true
				plugin= {"json:target/cucumber-reports/cucumber.json"}
		)



public class ADRunnerTest {
	
}
