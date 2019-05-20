package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		 features = "/home/knolly/workspace/adtestautomation/src/main/java/features"
		,glue={"stepDefinitions"}
	)

public class ADRunner {

}
